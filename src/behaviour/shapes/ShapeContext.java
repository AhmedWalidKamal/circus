package behaviour.shapes;

import controller.MainController;
import model.shapes.Shape;

/**
 * Acts as a controller for the many consequent states a shape can be in,
 */
public final class ShapeContext {
    /**
     * Current {@link ShapeState}.
     */
    private ShapeState shapeState = null;

    private MainController mainController = null;

    /**
     * {@link Shape} to apply states on.
     */
    private Shape shape = null;

    /**
     * Constructs a new Context for shape.
     * @param shape {@link Shape} shape to apply states on.
     */
    public ShapeContext(final Shape shape, final MainController mainController) {
        this.shape = shape;
        this.mainController = mainController;
        shapeState = new BeforeAddingState(mainController, this);
    }

    /**
     * Handles current shape state.
     */
    public void handle() {
        shapeState.handle(shape);
    }
}
