package behaviour.shapes;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import util.Shelf;

//TODO: Make the actual sliding transition here.
class SlidingState implements ShapeState {

    private ShapeContext context = null;
    private static final double EDGE_DISTANCE = 10;
    private Shelf shelf = null;
    private Path path = null;

    protected SlidingState(final ShapeContext context) {
        this.context = context;
        path = new Path();
    }

    @Override
    public final void handle() {
        shelf = context.getGameUtilController().getNextShelf();
        double x = 0;
        switch (shelf.getOrientation()) {
            case LEFT:
                x = shelf.getImageView().getImage().getWidth() - EDGE_DISTANCE;
                path.getElements().add(new MoveTo(0, shelf.getY()));
                context.getShape().setX(0);
                context.getShape().setY(shelf.getY());
                path.getElements().add(new LineTo(x, shelf.getY()));
                break;
            case RIGHT:
                x = context.getViewController().getRootPanePrefWidth()
                        - shelf.getImageView().getImage().getWidth() + EDGE_DISTANCE;
                path.getElements().add(new MoveTo(context.getViewController()
                        .getRootPanePrefWidth(), shelf.getY()));
                context.getShape().setX(context.getViewController().getRootPanePrefWidth());
                context.getShape().setY(shelf.getY());
                path.getElements().add(new LineTo(x, shelf.getY()));
                break;
            default:
                break;
        }
        context.getViewController().addToRootPane(context.getShape().getImageView());
        goNext();
    }

    @Override
    public ShapeContext getContext() {
        return context;
    }

    @Override
    public void setContext(final ShapeContext context) {
        this.context = context;
    }

    private final void goNext() {
        final FallingState fallingState = new FallingState(context,
                shelf, path);
        fallingState.addObserver(new FallingStateObserver());
        context.setShapeState(fallingState);
        context.handle();
    }
}
