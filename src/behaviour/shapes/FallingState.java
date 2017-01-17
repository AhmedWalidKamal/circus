package behaviour.shapes;

import controller.MainController;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.Shelf;
import model.shapes.Shape;

class FallingState implements ShapeState {
    private MainController mainController;
    private Path path;
    private Shelf shelf;

    protected FallingState(final MainController mainController, Path path,Shelf shelf) {
        this.mainController = mainController;
        this.path = path;
        this.shelf=shelf;
    }

    @Override
    public final void handle(final Shape shape) {
        Pane root = mainController.getGameView().getRootPane();
        long prevTime = System.nanoTime();
        switch (shelf.getOrientation()) {
            case LEFT:
            while (shape.getImageView().getX() < root.prefWidthProperty().doubleValue()
                    && shape.getImageView().getY() < root.prefHeightProperty().doubleValue()) {
                long currentTime = System.nanoTime();
                double dt = 3 * (currentTime - prevTime) / 1e8;
//            change x and y coor according to time
                double x = shape.getImageView().getX() + 0.4 * dt * shape.getImageView().getX();
                double y = shape.getImageView().getY() + 2 * dt * 0.98 * shape.getImageView().getY();
                prevTime = currentTime;
                path.getElements().add(new LineTo(x, y));
                PathTransition transition = new PathTransition();
                transition.setNode(shape.getImageView());
                transition.setPath(path);
                transition.setDuration(Duration.seconds(8));
                transition.play();
                shape.getImageView().setX(x);
                shape.getImageView().setY(y);
                //check the condition if the plate is in a position to be fetched or not.
                //then go to next state and perform an action according to it
                //System.out.println(x + " " + y);
            }
            break;
            case RIGHT:
            while (shape.getImageView().getX() < root.prefWidthProperty().doubleValue()
                    && shape.getImageView().getY() < root.prefHeightProperty().doubleValue()) {
                long currentTime = System.nanoTime();
                double dt = 3 * (currentTime - prevTime) / 1e8;
//            change x and y coor according to time
                double x = shape.getImageView().getX() - 0.4 * dt * shape.getImageView().getX();
                double y = shape.getImageView().getY() + 2 * dt * 0.98 * shape.getImageView().getY();
                prevTime = currentTime;
                path.getElements().add(new LineTo(x, y));
                PathTransition transition = new PathTransition();
                transition.setNode(shape.getImageView());
                transition.setPath(path);
                transition.setDuration(Duration.seconds(8));
                transition.play();
                shape.getImageView().setX(x);
                shape.getImageView().setY(y);
                //check the condition if the plate is in a position to be fetched or not.
                //then go to next state and perform an action according to it
               // System.out.println(x + " " + y);
            }
            break;
            default:
                break;
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
}
