package behaviour.shapes;

import model.shapes.Shape;

public interface ShapeState {

    /**
     * Handles a shape whenever it is in some state.
     * @param shape Shape to be handled.
     */
    public void handle(final Shape shape);

    /**
     * Checks if this state has next state or not.
     * @return Boolean value defining whether there is a next state or not.
     */
    public boolean hasNext();

    /**
     * Transits {@link ShapeContext} to next state.
     * @param context {@link ShapeContext} context shape is in at the moment.
     */
    public void goNext(final ShapeContext context);
}
