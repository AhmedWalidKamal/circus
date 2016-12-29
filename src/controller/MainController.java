package controller;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import view.gui.gameplay.GameView;

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

    private GameView gameView = null;

    public MainController() {
        inputController = new InputController(this);
        playersController = new PlayersController(this);
        shapesController = new ShapesController(this);
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

    /**
     * Sets {@link GameView} to the main controller of the main MVC.
     * @param gameView
     */
    public void setGameView(final GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Gets {@link GameView} of the main MVC.
     * @return
     */
    public GameView getGameView() {
        return this.gameView;
    }

    /**
     * Adds a {@link Node} to the root {@link Pane} of a {@link GameView}.
     * @param node Node to be added to root pane.
     */
    public void addToRootPane(final Node node) {
        gameView.getRootPane().getChildren().add(node);
    }

    /**
     * Starts a new game, calls control to run over view (e.g: render players,
     * create shapes and move them.. etc).
     */
    public void startNewGame() {
        playersController.start();
    }
}
