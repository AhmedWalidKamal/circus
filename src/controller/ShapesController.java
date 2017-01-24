package controller;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapePool;
import javafx.application.Platform;
import model.shapes.Shape;
import util.PauseableThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a controller to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class ShapesController extends PauseableThread {
    /**
     * {@link MainController} reference.
     */
    private MainController mainController = null;
    private ShapePool shapePool = null;
    private Thread actualThread = null;
    private boolean paused = false;
    private List<PauseableThread> runningThreads = null;

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
        this.shapePool = new ShapePool();
        runningThreads = new ArrayList<>();
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
            if (!paused) {
                final Thread plateThread = new Thread("plate" + counter) {
                    @Override
                    public void run() {
                        Shape shape = shapePool.create();
                        ShapeContext context = new ShapeContext(shape,
                                ShapesController.this,
                                mainController.getViewController(),
                                mainController.getGameUtilController(),
                                mainController.getPlayersController(),mainController.getLevelsController(), shapePool);

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

    public void addRunningShapeThread(PauseableThread thread) {
        runningThreads.add(thread);
    }

    public void removeRunningShapeThread(PauseableThread thread) {
        runningThreads.remove(thread);
    }

    @Override
    public void pauseThread() {
        for (PauseableThread thread : runningThreads) {
            thread.pauseThread();
        }
        paused = true;
    }

    @Override
    public void resumeThread() {
        for (PauseableThread thread : runningThreads) {
            thread.resumeThread();
        }
        paused = false;
    }
}