package behaviour.shapes;

import controller.MainController;
import javafx.animation.PathTransition;
import javafx.scene.layout.AnchorPane;
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
                path.getElements().add(new MoveTo(350,shelf.getImageView().getFitWidth()));
                PathTransition leftTransition = new PathTransition();
                leftTransition.setDuration(Duration.seconds(8));
                leftTransition.setPath(path);
                leftTransition.setNode(shape.getImageView());
                leftTransition.play();
                shape.getImageView().setX(shelf.getImageView().getFitWidth());
                break;
            case RIGHT:
                //Note : change fixed dimension later.
                path.getElements().add(new MoveTo(mainController.getGameView().getRootPane().getWidth() - 350 , -shelf.getImageView().getFitWidth()));
                PathTransition rightTransition = new PathTransition();
                rightTransition.setDuration(Duration.seconds(8));
                rightTransition.setPath(path);
                rightTransition.setNode(shape.getImageView());
                rightTransition.play();
                shape.getImageView().setX(shelf.getImageView().getFitWidth());
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
