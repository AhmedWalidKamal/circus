package behaviour.shapes;

import controller.MainController;
import javafx.animation.PathTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.Shelf;
import model.shapes.Shape;

class BeforeAddingState implements ShapeState {

    private MainController mainController = null;
    private Path path = new Path();
    protected BeforeAddingState(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public final void handle(final Shape shape) {
        mainController.getGameView().getRootPane()
                .getChildren().add(shape.getImageView());
        Shelf shelf = mainController.getGameUtilController().getNextShelf();
        switch (shelf.getOrientation()) {
            case LEFT:
                //Note : change fixed dimension later.
                path.getElements().add(new MoveTo(350,shelf.getY()));
                path.getElements().add(new LineTo(shelf.getImageView().getImage().getWidth(),shelf.getY()));
                break;
            case RIGHT:
                //Note : change fixed dimension later.
                path.getElements().add(new MoveTo(mainController.getGameView().getRootPane().getWidth() - 350 , -shelf.getY()));
                path.getElements().add(new LineTo(-shelf.getImageView().getImage().getWidth(),shelf.getY()));
                break;
            default:
                break;
        }
        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(8));
        transition.setPath(path);
        transition.setNode(shape.getImageView());
        transition.play();
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
