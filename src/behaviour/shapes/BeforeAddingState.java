package behaviour.shapes;

import model.shapes.Shape;

class BeforeAddingState implements ShapeState {

    @Override
    public final void handle(final Shape shape) {

    }

    @Override
    public final boolean hasNext() {
        return true;
    }

    @Override
    public final void goNext(final ShapeContext context) {
        context.setShapeState(new FallingState());
    }
}
