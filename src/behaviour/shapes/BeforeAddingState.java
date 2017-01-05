package behaviour.shapes;

import controller.MainController;
import model.Shelf;
import model.shapes.Shape;

class BeforeAddingState implements ShapeState {

    private MainController mainController = null;

    protected BeforeAddingState(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public final void handle(final Shape shape) {
        Shelf shelf = mainController.getGameUtilController().getNextShelf();
        double y = shelf.getY();
        switch (shelf.getOrientation()) {
            case LEFT:
                break;
            case RIGHT:
                break;
            default:
                break;
        }
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
