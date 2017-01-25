package behaviour.shapes;

import java.util.*;

import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.util.Pair;
import logs.LogsManager;
import model.Player;
import util.PauseableThread;
import util.Shelf;
import model.shapes.Shape;
import util.Point;

class FallingState extends Observable implements ShapeState {
    private Path path = null;
    private Shelf shelf = null;
    private boolean lock = false;
    private ShapeContext context = null;
    private boolean horizontal = true;
    private List<Observer> observers = null;
    private Queue<Transition> transitionQueue = new LinkedList<>();
    private boolean paused = false;

    protected FallingState(final ShapeContext context,
                           final Shelf shelf, final Path path) {
        this.path = path;
        this.shelf = shelf;
        this.context = context;
        observers = new ArrayList<>();
        transitionQueue = new LinkedList<>();
    }

    @Override
    public final void handle() {
        Shape shape = context.getShape();
        shape.setState(Shape.State.FALLING);
        ImageView shapeImageView = context.getShapeImageView();
        final PauseableThread control = new PauseableThread() {
            @Override
            public void run() {
                while (shape.getState() == Shape.State.FALLING) {
                    if (paused) {
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    if (!lock) {
                        final Point nextPoint = getNextTransitionPoint(shapeImageView);
                        if (nextPoint == null) {
                            shape.setState(Shape.State.NOT_FETCHED);
                            goNext(null);
                            break;
                        }
                        final Transition transition = getNextTransition(nextPoint,
                                shapeImageView);
                        transitionQueue.add(transition);
                        lock = true;
                        transition.setOnFinished(event -> {
                            lock = false;
                            transitionQueue.poll();
                            notifyObservers(new Pair<>(shape, shapeImageView));
                            interrupt();
                        });
                        Platform.runLater(() -> {
                            lock = true;
                            horizontal = false;
                            transition.play();
                            shapeImageView.setX(nextPoint.getX());
                            shapeImageView.setY(nextPoint.getY());
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
                context.getShapesController().removeRunningShapeThread(this);
            }

            @Override
            public void pauseThread() {
                if (!transitionQueue.isEmpty()) {
                    transitionQueue.peek().pause();
                }
                paused = true;
            }

            @Override
            public void resumeThread() {
                if (!transitionQueue.isEmpty()) {
                    transitionQueue.peek().play();
                }
                paused = false;
            }
        };
        control.setDaemon(true);
        context.getShapesController().addRunningShapeThread(control);
        control.start();
    }

    @Override
    public ShapeContext getContext() {
        return this.context;
    }

    @Override
    public void setContext(final ShapeContext context) {
        this.context = context;
    }

    protected final void goNext(final Player player) {
    	if (context.getShape().getState() == Shape.State.FETCHED) {
            LogsManager.getInstance().info("SHAPE FETCHED BY "+player.getPlayerName());
    	    FetchedState fetchedState = new FetchedState(context, player);
    	    context.setShapeState(fetchedState);
    	    context.getShapesController().putFetchedShape(context.getShapeImageView()
                    , context.getShape());
    	    context.handle();
        } else {
    	    AddedToShapePoolState addedToShapePoolState =
                    new AddedToShapePoolState(context);
            context.setShapeState(addedToShapePoolState);
            context.handle();
        }
    }

    private Point getNextTransitionPoint(final ImageView shapeImage) {
        if (horizontal) {
            double x = 0;
            final double y = shelf.getY();
            switch (shelf.getOrientation()) {
                case LEFT:
                    x = shelf.getImageView().getImage().getWidth() - 10;
                    break;
                case RIGHT:
                    x = context.getViewController().getRootPanePrefWidth()
                            - shelf.getImageView().getImage().getWidth() + 10;
                    break;
                default:
                    break;
            }
            return new Point(x, y);
        }
        final double dt = context.getLevelsController().getDifficultyLevel().getFallingRate();
        double x = 0;
        switch (shelf.getOrientation()) {
            case LEFT:
                x = shapeImage.getX()
                        + 0.04 * dt * shapeImage.getX();
                break;
            case RIGHT:
                x = shapeImage.getX()
                        - 0.04 * dt * shapeImage.getX();
                break;
            default:
                break;
        }
        final double y = shapeImage.getY() + 0.1 * dt * shapeImage.getY();
        if (x > context.getViewController().getRootPanePrefWidth() || y > context
                .getViewController().getRootPanePrefHeight()) {
            return null;
        }
        return new Point(x, y);
    }

    private Transition getNextTransition(final Point point, final ImageView shapeImageView) {
        final PathTransition transition = new PathTransition();
        transition.setNode(shapeImageView);
        if (horizontal) {
            transition.setPath(path);
            transition.setDuration(Duration.seconds(context.getLevelsController().getDifficultyLevel().getHorizontalDuration()));
            return transition;
        }
        final Path nextPath = new Path();
        nextPath.getElements().add(new MoveTo(shapeImageView.getX(), shapeImageView.getY()));
        nextPath.getElements().add(new LineTo(point.getX(), point.getY()));
        transition.setPath(nextPath);
        transition.setDuration(Duration.seconds(context.getLevelsController().getDifficultyLevel().getTransitionDuration()));
        return transition;
    }

    @Override
    public synchronized void addObserver(final Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(final Object shapeImageViewPair) {
        for (final Observer o : observers) {
            o.update(this, shapeImageViewPair);
        }
    }
}
