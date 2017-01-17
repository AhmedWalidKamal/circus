package behaviour.shapes;

import controller.MainController;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Player;
import model.Shelf;
import model.shapes.Shape;

import java.util.List;

class FallingState implements ShapeState {
    private MainController mainController;
    private Path path;
    private Shelf shelf;

    protected FallingState(final MainController mainController, Path path,Shelf shelf) {
        this.mainController = mainController;
        this.path = path;
        this.shelf = shelf;
    }

    @Override
    public final void handle(final Shape shape) {
        Pane root = mainController.getGameView().getRootPane();
        long prevTime = System.nanoTime();
        while (shape.getImageView().getX() < root.prefWidthProperty().doubleValue()
                && shape.getImageView().getY() < root.prefHeightProperty().doubleValue()) {
            long currTime = System.nanoTime();
            double dt = 3 * (currTime - prevTime) / 1e8;
            double x = 0;
            switch (shelf.getOrientation()) {
                case LEFT:
                    x = shape.getImageView().getX() + 0.4 * dt * shape.getImageView().getX();
                    break;
                case RIGHT:
                    x = shape.getImageView().getX() - 0.4 * dt * shape.getImageView().getX();
                default:
                    break;
            }
            double y = shape.getImageView().getY() + 2 * dt * 0.98 * shape.getImageView().getY();
            if (checkFetching(x, y)) {
                break;
            }
            prevTime = currTime;
            path.getElements().add(new LineTo(x, y));
            PathTransition transition = new PathTransition();
            transition.setNode(shape.getImageView());
            transition.setPath(path);
            transition.setDuration(Duration.seconds(8));
            transition.play();
            shape.getImageView().setX(x);
            shape.getImageView().setY(y);
        }
    }
//        path.getElements().add(new MoveTo(shape.getImageView().getX(), shape.getImageView().getY()));


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

    private boolean checkFetching(double x, double y) {
        List<Player> players = mainController.getPlayersController().getPlayers();
        for (Player player : players) {
            double leftStackX = player.getCharacter().getX()
                    + player.getLeftStackXInset();
            double leftStackY = player.getCharacter().getY()
                    - player.getLeftStackYInset();
            double rightStackX = player.getCharacter().getX()
                    + player.getRightStackXInset();
            double rightStackY = player.getCharacter().getY()
                    - player.getRightStackYInset();
            mainController.addToRootPane(new Rectangle(leftStackX, leftStackY, 10, 10));
            mainController.addToRootPane(new Rectangle(rightStackX, rightStackY, 10, 10));
        }
        return false;
    }
}
