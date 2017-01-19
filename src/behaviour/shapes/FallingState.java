package behaviour.shapes;

import controller.MainController;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.Player;
import model.Shelf;
import model.shapes.Shape;
import util.Point;

class FallingState implements ShapeState {
    private MainController mainController;
    private Path path;
    //TODO: Alternate with Shelf.Orientation instead.
    private Shelf shelf;
    private boolean lock = false;
    private enum State {
        FALLING, FETCHED, NOT_FETCHED;
    }
    private State state;
    private boolean horizontal = true;

    protected FallingState(final MainController mainController, final Path path,
                           final Shelf shelf) {
        this.mainController = mainController;
        this.path = path;
        this.shelf = shelf;
        state = State.FALLING;
    }

    @Override
    public final void handle(final Shape shape) {
        Thread control = new Thread("Plate Control") {
            @Override
            public void run() {
                while (state == FallingState.State.FALLING) {
                    if (!lock) {
                        Point nextPoint = getNextTransitionPoint(shape);
                        if (nextPoint == null) {
                            state = FallingState.State.NOT_FETCHED;
                            break;
                        }
                        Transition transition = getNextTransition(nextPoint,
                                shape.getImageView());
                        lock = true;
                        transition.setOnFinished(event -> {
                            lock = false;
                            if (checkFetching(nextPoint, shape)) {
                                state = FallingState.State.FETCHED;
                            } else {
                                lock = false;
                            }
                            interrupt();
                        });
                        Platform.runLater(() -> {
                            lock = true;
                            horizontal = false;
                            transition.play();
                            shape.getImageView().setX(nextPoint.getX());
                            shape.getImageView().setY(nextPoint.getY());
                        });
                        synchronized (this) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                continue;
                            }
                        }
                    }
                }
            }
        };
        control.setDaemon(true);
        control.start();
    }

    @Override
    public final boolean hasNext() {
        return true;
    }

    @Override
    public final void goNext(final ShapeContext context) {
        /*TODO: This might cause some problems as we need to block the main plate
        until the plate finishes transition but it's not possible as UI threads (Platform)
        cannot be blocked in any way possible (can't use wait/sleep ..etc).. until then...
         */
        Thread next = new Thread("Proceeding to next state") {
            @Override
            public void run() {
                while (state == FallingState.State.FALLING) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (state == FallingState.State.FETCHED) {
                        context.setShapeState(new FetchedState());
                    } else {
                        context.setShapeState(new AddedToShapePoolState());
                    }
                }
            }
        };
        next.setDaemon(true);
        next.start();
    }

    private Point getNextTransitionPoint(final Shape shape) {
        //TODO: Decrease number of lines in this method.
        if (horizontal) {
            double x = 0;
            double y = shelf.getY();
            switch (shelf.getOrientation()) {
                case LEFT:
                    x = shelf.getImageView().getImage().getWidth() - 10;
                    break;
                case RIGHT:
                    x = mainController.getGameView().getRootPane().getPrefWidth()
                            - shelf.getImageView().getImage().getWidth() + 10;
                    break;
                default:
                    break;
            }
            return new Point(x, y);
        }
        double dt = 0.1;
        double x = 0;
        switch (shelf.getOrientation()) {
            case LEFT:
                x = shape.getImageView().getX()
                        + 0.04 * dt * shape.getImageView().getX();
                break;
            case RIGHT:
                x = shape.getImageView().getX()
                        - 0.04 * dt * shape.getImageView().getX();
                break;
            default:
                break;
        }
        double y = shape.getImageView().getY() + 0.1 * dt *shape.getImageView().getY();
        Pane root = mainController.getGameView().getRootPane();
        if (x > root.prefWidthProperty().doubleValue() || y > root
                .prefHeightProperty().doubleValue()) {
            return null;
        }
        return new Point(x, y);
    }

    private Transition getNextTransition(final Point point, final ImageView shapeImageView) {
        PathTransition transition = new PathTransition();
        transition.setNode(shapeImageView);
        if (horizontal) {
            transition.setPath(path);
            transition.setDuration(Duration.seconds(0.8));
            return transition;
        }
        Path nextPath = new Path();
        nextPath.getElements().add(new MoveTo(shapeImageView.getX(), shapeImageView.getY()));
        nextPath.getElements().add(new LineTo(point.getX(), point.getY()));
        transition.setPath(nextPath);
        transition.setDuration(Duration.seconds(0.0001));
        return transition;
    }

    private boolean checkFetching(final Point point, final Shape shape) {
        //TODO: Make it more readable for regular human beings.
        for (Player player : mainController.getPlayersController().getPlayers()) {
            Point leftStack = new Point(player.getCharacter().getX() + player
                    .getLeftStackXInset(), player.getCharacter().getY() - player
                    .getLeftStackYInset());
            Point rightStack = new Point(player.getCharacter().getX() + player
                    .getRightStackXInset(), player.getCharacter().getY() - player
                    .getRightStackYInset());
            if (Math.abs(leftStack.getX() - point.getX()) <= shape.getImageView()
                    .getImage().getWidth() / 4
                    && Math.abs(leftStack.getY() - point.getY()) <= 5) {
                shape.setX(player.getCharacter().getImageView().getX());
                shape.getImageView().translateXProperty().bind(player.getCharacter()
                        .getImageView().translateXProperty());
                shape.setY(player.getCharacter().getY() - player.getLeftStackYInset());
                player.addToLeftStack(shape);
                return true;
            }
            if (Math.abs(rightStack.getX() - point.getX()) <= shape.getImageView()
                    .getImage().getWidth() / 4
                    && Math.abs(rightStack.getY() - point.getY()) <= 5) {
                shape.setX(player.getCharacter().getImageView().getX() + player
                        .getRightStackXInset() - player.getLeftStackXInset());
                shape.getImageView().translateXProperty().bind(player.getCharacter()
                        .getImageView().translateXProperty());
                shape.setY(player.getCharacter().getY() - player.getRightStackYInset());
                player.addToRightStack(shape);
                return true;
            }
        }
        return false;
    }
}