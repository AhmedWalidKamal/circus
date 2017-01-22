package behaviour.shapes;

import behaviour.shapes.util.ShapePool;
import controller.GameUtilController;
import controller.PlayersController;
import controller.ViewController;
import model.shapes.Shape;

/**
 * Acts as a controller for the many consequent states a shape can be in,
 */
public final class ShapeContext {
    /**
     * Current {@link ShapeState}.
     */
    private ShapeState shapeState = null;

    private ViewController viewController = null;

    private GameUtilController gameUtilController = null;

    private PlayersController playersController = null;

    /**
     * {@link Shape} to apply states on.
     */
    private Shape shape = null;

    private ShapePool shapePool = null;

    /**
     * Constructs a new Context for shape.
     * @param shape {@link Shape} shape to apply states on.
     */
    public ShapeContext(final Shape shape, final ViewController viewController,
                        final GameUtilController gameUtilController,
                        final PlayersController playersController,
                        final ShapePool shapePool) {
        this.shape = shape;
        this.viewController = viewController;
        this.gameUtilController = gameUtilController;
        this.playersController = playersController;
        this.shapePool = shapePool;
        shapeState = new SlidingState(this);
    }

    public ShapePool getShapePool() {
    	return this.shapePool;
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

    public void setShapeState(final ShapeState shapeState) {
        this.shapeState = shapeState;
    }

    public Shape getShape() {
        return shape;
    }

    /**
     * Handles current shape state.
     */
    public void handle() {
        shapeState.handle();
    }
}
