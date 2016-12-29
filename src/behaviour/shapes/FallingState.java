package behaviour.shapes;

import model.shapes.Shape;

class FallingState implements ShapeState {

    @Override
    public final void handle(final Shape shape) {

    }

    @Override
    public final boolean hasNext() {
        return true;
    }

    @Override
    public final void goNext(final ShapeContext context) {
        if (true) { //fetched
            context.setShapeState(new FetchedState());
        } else {
            context.setShapeState(new AddedToShapePoolState());
        }
    }
}
