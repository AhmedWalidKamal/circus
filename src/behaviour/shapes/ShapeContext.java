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
        shapeState = new BeforeAddingState(this.mainController);
    }

    /**
     * Handles current shape state.
     */
    public void handleShapeState() {
        shapeState.handle(shape);
    }

    /**
     * Transits state to next shape state.
     */
    public void goNext() {
        shapeState.goNext(this);
    }

    /**
     * Gets current shape state.
     * @return {@link ShapeState} current shape state.
     */
    public ShapeState getShapeState() {
        return shapeState;
    }

    /**
     * Sets current {@link ShapeState}.
     * @param shapeState {@link ShapeState} to be set.
     */
    public void setShapeState(final ShapeState shapeState) {
        this.shapeState = shapeState;
    }
}
