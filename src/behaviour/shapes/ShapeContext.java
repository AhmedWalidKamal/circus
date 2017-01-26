package behaviour.shapes;

import control.GameUtilController;
import control.LevelsController;
import control.MainController;
import control.PlayersController;
import control.ShapesController;
import control.ViewController;
import javafx.scene.image.ImageView;
import model.shapes.Shape;

/**
 * Acts as a control for the many consequent states a shape can be in,
 */
public final class ShapeContext {
    /**
     * Current {@link ShapeState}.
     */
    private ShapeState shapeState = null;

    private MainController mainController = null;
    /**
     * {@link Shape} to apply states on.
     */
    private Shape shape = null;

    private ImageView shapeImageView = null;

    /**
     * Constructs a new Context for shape.
     * @param shape {@link Shape} shape to apply states on.
     */
    public ShapeContext(final Shape shape, final ImageView shapeImageView,
                        final MainController mainController) {
        this.shape = shape;
        this.shapeImageView = shapeImageView;
        this.mainController = mainController;
        shapeState = new SlidingState(this);
    }

    public GameUtilController getGameUtilController() {
        return this.mainController.getGameUtilController();
    }

    public ViewController getViewController() {
        return this.mainController.getViewController();
    }

    public PlayersController getPlayersController() {
        return this.mainController.getPlayersController();
    }
    public LevelsController getLevelsController() {
        return this.mainController.getLevelsController();
    }

    public ShapesController getShapesController() {
        return this.mainController.getShapesController();
    }

    public void setShapeState(final ShapeState shapeState) {
        this.shapeState = shapeState;
    }

    public Shape getShape() {
        return shape;
    }

    public ImageView getShapeImageView() {
        return shapeImageView;
    }

    /**
     * Handles current shape state.
     */
    public void handle() {
        shapeState.handle();
    }
}
