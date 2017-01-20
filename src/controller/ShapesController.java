package controller;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapePool;
import javafx.application.Platform;
import model.shapes.Shape;

import java.util.Observable;
import java.util.Observer;

/**
 * Acts as a controller to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class  ShapesController  {
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
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        final Thread mainPlateThread = new Thread("Main Plate Thread") {
            private int counter = 0;
            @Override
            public void run() {
                while (true) {
                    final Thread thread = new Thread("Plate" + counter) {
                        @Override
                        public void run() {
                            final Shape shape = shapePool.create();
                            final ShapeContext context = new ShapeContext(shape, mainController);
                            context.handle();
                            //System.out.println("Went next");
                        }
                    };
                    thread.setDaemon(true);
                    Platform.runLater(thread);
                    counter = (counter + 1) % 1000;
                    try {
                        sleep(2000);
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
//                    break;
                }
            }
        };
        mainPlateThread.setDaemon(true);
        mainPlateThread.start();
    }

}