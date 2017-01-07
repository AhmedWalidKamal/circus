package behaviour.shapes;

import controller.MainController;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import model.Shelf;
import model.shapes.Shape;

class BeforeAddingState implements ShapeState {

    private MainController mainController = null;
    private static final double EDGE_DISTANCE = 10;
    private static final double DURATION = 4;
    Path path = new Path();

    protected BeforeAddingState(final MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public final void handle(Shape shape) {
        Shelf shelf = mainController.getGameUtilController().getNextShelf();
        double x = 0;
        switch (shelf.getOrientation()) {
            case LEFT:
                shape.setX(0);
                x = shelf.getImageView().getImage().getWidth() - EDGE_DISTANCE;
                path.getElements().add(new MoveTo(0, shelf.getY()));
                path.getElements().add(new LineTo(x, shelf.getY()));

                break;
            case RIGHT:
                shape.setX(mainController.getGameView().getRootPane()
                        .getPrefWidth());
                x = mainController.getGameView().getRootPane().getPrefWidth()
                        - shelf.getImageView().getImage().getWidth() + EDGE_DISTANCE;
                path.getElements().add(new MoveTo(mainController.getGameView()
                        .getRootPane().getPrefWidth(), shelf.getY()));
                path.getElements().add(new LineTo(x, shelf.getY()));
                break;
            default:
                break;
        }
        mainController.getGameView().getRootPane()
                .getChildren().add(shape.getImageView());

//        transition.setDuration(Duration.seconds(8));
//        transition.setPath(path);
//        transition.setNode(shape.getImageView());
//        transition.setOnFinished(event -> notifyObservers());
//        transition.play();
        shape.getImageView().setX(x);
        shape.getImageView().setY(shelf.getY());
    }

    @Override
    public final boolean hasNext() {
        return true;
    }

    @Override
    public final void goNext(final ShapeContext context) {
        context.setShapeState(new FallingState(mainController, path));
    }
}
