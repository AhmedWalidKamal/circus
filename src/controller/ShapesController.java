package controller;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapeCreator;
import behaviour.shapes.util.ShapePool;
import model.shapes.Shape;

/**
 * Acts as a controller to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class ShapesController {
    /**
     * {@link MainController} reference.
     */
    private MainController mainController = null;

    private ShapeCreator shapeCreator = null;

    private ShapePool shapePool = null;

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
        this.shapeCreator = new ShapeCreator();
        this.shapePool = new ShapePool();
    }

    public void startGame() {
        try {
            Class.forName("model.shapes.Plate");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Shape shape = shapePool.create();
        ShapeContext context = new ShapeContext(shape, mainController);
        context.handleShapeState();
    }
}
