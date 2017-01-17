package controller;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapePool;
import javafx.application.Platform;
import model.shapes.Shape;

/**
 * Acts as a controller to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class  ShapesController {
    /**
     * {@link MainController} reference.
     */
    private MainController mainController = null;
    private ShapePool shapePool = null;

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
        this.shapePool = new ShapePool();
    }

    public void startGame() {
        try {
            Class.forName("model.shapes.Plate");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Thread mainPlateThread = new Thread("Main Plates") {
            private int counter = 0;
            @Override
            public void run() {
                while (true) {
                    Thread thread = new Thread("Plate" + counter) {
                        @Override
                        public void run() {
                            Shape shape = shapePool.create();
                            ShapeContext context = new ShapeContext(shape, mainController);

                            context.handleShapeState();
                            context.goNext();
                            context.handleShapeState();
                        }
                    };
                    thread.setDaemon(true);
                    Platform.runLater(thread);
                    counter++;
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mainPlateThread.setDaemon(true);
        mainPlateThread.start();
    }
}