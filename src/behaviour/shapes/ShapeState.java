package behaviour.shapes;

import model.shapes.Shape;

public interface ShapeState {

    /**
     * Handles a shape whenever it is in some state.
     * @param shape Shape to be handled.
     */
    void handle(final Shape shape);
}
