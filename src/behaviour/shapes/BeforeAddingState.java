package behaviour.shapes;

import controller.MainController;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import model.Shelf;
import model.shapes.Shape;

class BeforeAddingState implements ShapeState {

    private MainController mainController = null;
    private ShapeContext context = null;
    private static final double EDGE_DISTANCE = 10;
    private static final double DURATION = 4;
    private Path path = null;
    private Shelf shelf = null;

    protected BeforeAddingState(final MainController mainController,
                                final ShapeContext context) {
        this.mainController = mainController;
        this.context = context;
        this.path = new Path();
    }

    @Override
    public final void handle(final Shape shape) {
        shelf = mainController.getGameUtilController().getNextShelf();
        double x = 0;
        switch (shelf.getOrientation()) {
            case LEFT:
                x = shelf.getImageView().getImage().getWidth() - EDGE_DISTANCE;
                path.getElements().add(new MoveTo(0, shelf.getY()));
                shape.setX(0);
                shape.setY(shelf.getY());
                path.getElements().add(new LineTo(x, shelf.getY()));
                break;
            case RIGHT:
                x = mainController.getGameView().getRootPane().getPrefWidth()
                        - shelf.getImageView().getImage().getWidth() + EDGE_DISTANCE;
                path.getElements().add(new MoveTo(mainController.getGameView()
                        .getRootPane().getPrefWidth(), shelf.getY()));
                shape.setX(mainController.getGameView().getRootPane()
                        .getPrefWidth());
                shape.setY(shelf.getY());
                path.getElements().add(new LineTo(x, shelf.getY()));
                break;
            default:
                break;
        }
        mainController.getGameView().getRootPane()
                .getChildren().add(shape.getImageView());
        goNext(shape);
    }

    private final void goNext(final Shape shape) {
        new FallingState(mainController, path, shelf, context).handle(shape);
    }
}
