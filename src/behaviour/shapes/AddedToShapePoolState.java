package behaviour.shapes;

import model.shapes.Shape;

class AddedToShapePoolState implements ShapeState {

    @Override
    public final void handle(final Shape shape) {
    	System.out.println("Shape fell off the screen");
    }

    @Override
    public ShapeContext getContext() {
        return null;
    }

    @Override
    public void setContext(final ShapeContext context) {

    }
}
