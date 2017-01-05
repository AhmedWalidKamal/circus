package controller;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapeCreator;
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

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
    }

    public void startGame() {
        Shape shape = ShapeCreator.createShape();
        ShapeContext context = new ShapeContext(shape, mainController);
    }
}
