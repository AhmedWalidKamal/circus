package behaviour.shapes;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import model.shapes.Shape;
import util.Slider;

//TODO: Make the actual sliding transition here.
class SlidingState implements ShapeState {

    private ShapeContext context = null;
    private static final double EDGE_DISTANCE = 10;
    private Slider slider = null;
    private Path path = null;

    protected SlidingState(final ShapeContext context) {
        this.context = context;
        path = new Path();
    }

    @Override
    public final void handle() {
        context.getShape().setState(Shape.State.SLIDING);
        slider = context.getGameUtilController().getNextSlider();
        double x;
        switch (slider.getOrientation()) {
            case LEFT:
                x = slider.getImageView().getImage().getWidth() - EDGE_DISTANCE;
                path.getElements().add(new MoveTo(0, slider.getY()));
                context.getShapeImageView().setX(0);
                context.getShapeImageView().setY(slider.getY());
                path.getElements().add(new LineTo(x, slider.getY()));
                break;
            case RIGHT:
                x = context.getViewController().getRootPanePrefWidth()
                        - slider.getImageView().getImage().getWidth() + EDGE_DISTANCE;
                path.getElements().add(new MoveTo(context.getViewController()
                        .getRootPanePrefWidth(), slider.getY()));
                context.getShapeImageView().setX(context.getViewController().getRootPanePrefWidth());
                context.getShapeImageView().setY(slider.getY());
                path.getElements().add(new LineTo(x, slider.getY()));
                break;
            default:
                break;
        }
        context.getViewController().addToRootPane(context.getShapeImageView());
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
                slider, path);
        fallingState.addObserver(new FallingStateObserver());
        context.setShapeState(fallingState);
        context.handle();
    }
}
