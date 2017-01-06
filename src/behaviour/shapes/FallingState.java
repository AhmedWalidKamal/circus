package behaviour.shapes;

import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.shapes.Shape;

class FallingState implements ShapeState {
    @Override
    public final void handle(final Shape shape) {
        Path path = new Path();
        long prevTime = System.nanoTime();
        path.getElements().add(new MoveTo(shape.getImageView().getX(),shape.getImageView().getY()));
        while (shape.getImageView().getX() < 1024 && shape.getImageView().getY() < 800){
            long currentTime = System.nanoTime();
            double dt = 3 * (currentTime - prevTime) / 1e8;
            //change x and y coor according to time
            double x = shape.getImageView().getX() + dt * shape.getImageView().getX();
            double y = shape.getImageView().getY() + 2 * dt * 0.98 * shape.getImageView().getY();
            prevTime = currentTime;
            path.getElements().add(new LineTo(x, y));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(8));
            pathTransition.setPath(path);
            pathTransition.setNode(shape.getImageView());
            pathTransition.play();
            shape.getImageView().setX(x);
            shape.getImageView().setY(y);
            ///check the condition if the plate is in a position to be fetched or not.
            ///then go to next state and perform an action according to it
        }
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
}
