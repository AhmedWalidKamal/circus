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

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

class FallingState extends Observable implements ShapeState {
    private MainController mainController = null;
    private Path path = null;
    //TODO: Alternate with Shelf.Orientation instead.
    private Shelf shelf = null;
    private boolean lock = false;
    private ShapeContext context = null;
    protected enum State {
        FALLING, FETCHED, NOT_FETCHED;
    }
    private State state;
    private Player player = null;
    private boolean horizontal = true;
    private List<Observer> observers = null;

    protected FallingState(final MainController mainController, final Path path,
                           final Shelf shelf, final ShapeContext context) {
        this.mainController = mainController;
        this.path = path;
        this.shelf = shelf;
        this.context = context;
        state = State.FALLING;
        observers = new ArrayList<>();
    }

    @Override
    public final void handle(final Shape shape) {
        final Thread control = new Thread("Plate Control") {
            @Override
            public void run() {
                while (state == FallingState.State.FALLING) {
                    if (!lock) {
                        final Point nextPoint = getNextTransitionPoint(shape);
                        if (nextPoint == null) {
                            state = FallingState.State.NOT_FETCHED;
                            break;
                        }
                        final Transition transition = getNextTransition(nextPoint,
                                shape.getImageView());
                        lock = true;
                        transition.setOnFinished(event -> {
                            lock = false;
                            notifyObservers(shape);
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
                            } catch (final InterruptedException e) {
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
    public ShapeContext getContext() {
        return context;
    }


    @Override
    public void setContext(final ShapeContext context) {
        this.context = context;
    }

    protected final void goNext(final Shape shape, final Player player) {
        if (state == FallingState.State.FETCHED) {
            new FetchedState(mainController, player, context).handle(shape);
        } else {
            //hehe
        }
    }

    private Point getNextTransitionPoint(final Shape shape) {
        //TODO: Decrease number of lines in this method.
        if (horizontal) {
            double x = 0;
            final double y = shelf.getY();
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
        final double dt = 0.1;
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
        final double y = shape.getImageView().getY() + 0.1 * dt *shape.getImageView().getY();
        final Pane root = mainController.getGameView().getRootPane();
        if (x > root.prefWidthProperty().doubleValue() || y > root
                .prefHeightProperty().doubleValue()) {
            return null;
        }
        return new Point(x, y);
    }

    private Transition getNextTransition(final Point point, final ImageView shapeImageView) {
        final PathTransition transition = new PathTransition();
        transition.setNode(shapeImageView);
        if (horizontal) {
            transition.setPath(path);
            transition.setDuration(Duration.seconds(0.8));
            return transition;
        }
        final Path nextPath = new Path();
        nextPath.getElements().add(new MoveTo(shapeImageView.getX(), shapeImageView.getY()));
        nextPath.getElements().add(new LineTo(point.getX(), point.getY()));
        transition.setPath(nextPath);
        transition.setDuration(Duration.seconds(0.0001));
        return transition;
    }

    private Player fetch(final Point point, final Shape shape) {
        //TODO: Make it more readable for regular human beings.
        for (final Player player : mainController.getPlayersController().getPlayers()) {
            final Point leftStack = new Point(player.getCharacter().getX() + player
                    .getLeftStackXInset(), player.getCharacter().getY() - player
                    .getLeftStackYInset());
            final Point rightStack = new Point(player.getCharacter().getX() + player
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
                return player;
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
                return player;
            }
        }
        return null;
    }

    protected void setState(final State state) {
        this.state = state;
    }

    @Override
    public synchronized void addObserver(final Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(final Object shape) {
        for (Observer o : observers) {
            o.update(this, shape);
        }
    }
}