package behaviour.shapes;

import controller.MainController;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.Shelf;
import model.shapes.Shape;

class BeforeAddingState implements ShapeState {

    private MainController mainController = null;
    private static final double EDGE_DISTANCE = 10;
    private static final double DURATION = 4;
    Path path = new Path();
    Shelf shelf;

    protected BeforeAddingState(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public final void handle(Shape shape) {
        shelf = mainController.getGameUtilController().getNextShelf();
        double x = 0;
        switch (shelf.getOrientation()) {
            case LEFT:
                x = shelf.getImageView().getImage().getWidth() - EDGE_DISTANCE;
                path.getElements().add(new MoveTo(0, shelf.getY()));
                shape.setX(0);
                shape.setY(shelf.getY());
                path.getElements().add(new LineTo(x, shelf.getY()));

                break;
            case RIGHT:
                x = mainController.getGameView().getRootPane().getPrefWidth()
                        - shelf.getImageView().getImage().getWidth() + EDGE_DISTANCE;
                path.getElements().add(new MoveTo(mainController.getGameView()
                        .getRootPane().getPrefWidth(), shelf.getY()));
                shape.setX(mainController.getGameView().getRootPane()
                        .getPrefWidth());
                shape.setY(shelf.getY());
                path.getElements().add(new LineTo(x, shelf.getY()));
                break;
            default:
                break;
        }
        mainController.getGameView().getRootPane()
                .getChildren().add(shape.getImageView());
    }

    @Override
    public final boolean hasNext() {
        return true;
    }

    @Override
    public final void goNext(final ShapeContext context) {
        context.setShapeState(new FallingState(mainController, path, shelf));
    }
}
