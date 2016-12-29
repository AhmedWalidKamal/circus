package behaviour.shapes;

import model.shapes.Shape;

class FetchedState implements ShapeState {

    @Override
    public final void handle(final Shape shape) {

    }

    @Override
    public final boolean hasNext() {
        return false;
    }

    @Override
    public final void goNext(final ShapeContext context) {

    }
}
