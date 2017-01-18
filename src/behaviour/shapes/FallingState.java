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
    private Shelf shelf;
    private boolean lock = false;
    private boolean stopped = false;
    private static final double FETCING_INSET = 5;
    private boolean horizontal = true;
    int counter = 0;

    protected FallingState(final MainController mainController, Path path,Shelf shelf) {
        this.mainController = mainController;
        this.path = path;
        this.shelf = shelf;
    }

    @Override
    public final void handle(final Shape shape) {
        Thread control = new Thread("Plate Control") {
            @Override
            public void run() {
                int counter = 0;
                while (!stopped) {
                    if (!lock) {
                        Point nextPoint = getNextTransitionPoint(shape);
                        if (nextPoint == null) {
                            stopped = true;
                            break;
                        }
                        Transition transition = getNextTransition(nextPoint,
                                shape.getImageView());
                        lock = true;
                        transition.setOnFinished(event -> {
//                            System.out.println("Finished");
                            lock = false;
                            if (checkFetching(nextPoint)) {
                                stopped = true;
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
//                                System.out.println(nextPoint.getX() + " " + nextPoint.getY());
                                counter++;
                                continue;
                            }
                        }
                    }
                }
                System.out.println("-------->" + counter);

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
        if (true) { //fetched
            context.setShapeState(new FetchedState());
        } else {
            context.setShapeState(new AddedToShapePoolState());
        }
    }

    private Point getNextTransitionPoint(final Shape shape) {
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

    private boolean checkFetching(final Point point) {
        for (Player player : mainController.getPlayersController().getPlayers()) {
            double leftStackX = player.getCharacter().getX()
                    + player.getLeftStackXInset();
            double leftStackY = player.getCharacter().getY()
                    - player.getLeftStackYInset();
            double rightStackX = player.getCharacter().getX()
                    + player.getRightStackXInset();
            double rightStackY = player.getCharacter().getY()
                    - player.getRightStackYInset();
            if (Math.abs(leftStackX - point.getX()) <= 10
                    && Math.abs(point.getY() - rightStackY ) <= 5) {
                System.out.println("yeah fam");
            }
            if (Math.abs(point.getY() - leftStackY) <= 5
                    && Math.abs(point.getX() - leftStackX) <= 25) {
                return true;
            } else if (Math.abs(point.getY() - rightStackY) <= 5 && Math.abs(
                    point.getX() - rightStackX) <= 25) {
                return true;
            }
        }
        return false;
    }
}
