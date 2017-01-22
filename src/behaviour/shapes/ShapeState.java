package behaviour.shapes;

import model.shapes.Shape;

public interface ShapeState {

    /**
     * Handles a shape whenever it is in some state (gets the shape from its
     * pre-defined {@link ShapeContext}.
     */
    void handle();

    ShapeContext getContext();

    void setContext(final ShapeContext context);
}
