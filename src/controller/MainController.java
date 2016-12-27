package controller;

/**
 * Acts as the Main Controller for MVC, has references to sub-controllers each
 * is used to control a single task, also acts as a link between model and view.
 */
public class MainController {
    /**
     * {@link InputController} instance.
     */
    private InputController inputController = null;

    /**
     * {@link PlayersController} instance.
     */
    private PlayersController playersController = null;

    /**
     * {@link ShapesController} instance.
     */
    private ShapesController shapesController = null;

    /**
     * Constructs new instances from sub-controllers.
     */
    public MainController() {
        inputController = new InputController();
        playersController = new PlayersController();
        shapesController = new ShapesController();
    }

    /**
     * Gets an instance to {@link InputController} which allows control on
     * input.
     * @return {@link InputController} instance.
     */
    public InputController getInputController() {
        return this.inputController;
    }

    /**
     * Gets an instance to {@link PlayersController} which allows control on
     * players.
     * @return {@link PlayersController} instance.
     */
    public PlayersController getPlayersController() {
        return this.playersController;
    }

    /**
     * Gets an instance to {@link ShapesController} which allows control on
     * shapes.
     * @return {@link ShapesController} instance.
     */
    public ShapesController getShapesController() {
        return this.shapesController;
    }
}
