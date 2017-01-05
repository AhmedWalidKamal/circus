package behaviour.shapes;

import model.shapes.Shape;

import java.util.Observable;

class FetchedState extends Observable implements ShapeState {

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

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}
