package controller;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapePool;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.shape.Rectangle;
import model.shapes.Shape;
import util.PausableThread;

/**
 * Acts as a controller to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class ShapesController extends PausableThread {
    /**
     * {@link MainController} reference.
     */
    private MainController mainController = null;
    private ShapePool shapePool = null;
    private Thread actualThread = null;
    private boolean pause = false;

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
        this.shapePool = new ShapePool();
    }

    @Override
    public void run() {
        int counter = 0;
        try {
            Class.forName("model.shapes.Plate");
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            if (!pause) {
                final Thread plateThread = new Thread("plate" + counter) {
                    @Override
                    public void run() {
                        Shape shape = shapePool.create();
                        ShapeContext context = new ShapeContext(shape,
                                mainController.getViewController(),
                                mainController.getGameUtilController(),
                                mainController.getPlayersController(), shapePool);

                        context.handle();
                    }
                };
                plateThread.setDaemon(true);
                Platform.runLater(plateThread);
                counter = (counter + 1) % 1000;
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void pauseThread() {
        pause = true;
    }

    @Override
    public void resumeThread() {
        pause = false;
    }
}