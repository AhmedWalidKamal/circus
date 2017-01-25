package behaviour.shapes;

import behaviour.shapes.util.ShapePool;
import control.GameUtilController;
import control.LevelsController;
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

    private ViewController viewController = null;

    private GameUtilController gameUtilController = null;

    private PlayersController playersController = null;

    private ShapesController shapesController = null;

    private LevelsController levelsController=null;

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
                        final ShapesController shapesController,
                        final ViewController viewController,
                        final GameUtilController gameUtilController,
                        final PlayersController playersController,
                        final LevelsController levelsController) {
        this.shape = shape;
        this.shapeImageView = shapeImageView;
        this.shapesController = shapesController;
        this.viewController = viewController;
        this.gameUtilController = gameUtilController;
        this.playersController = playersController;
        this.levelsController = levelsController;
        shapeState = new SlidingState(this);
    }

    public GameUtilController getGameUtilController() {
        return this.gameUtilController;
    }

    public ViewController getViewController() {
        return this.viewController;
    }

    public PlayersController getPlayersController() {
        return playersController;
    }
    public LevelsController getLevelsController() {
        return this.levelsController;
    }

    public ShapesController getShapesController() {
        return shapesController;
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
