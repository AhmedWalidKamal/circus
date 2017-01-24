package controller;

import view.gui.endgame.EndGameView;
import view.gui.gameplay.GameView;
import view.gui.gameplay.GameViewController;

/**
 * Acts as the Main Controller for MVC, has references to sub-controllers each
 * is used to control a single task, also acts as a link between model and view.
 */
public final class MainController {
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
     * {@link GameUtilController} instance.
     */
    private GameUtilController gameUtilController = null;
    /**
     * {@link GameViewController} instance.
     */
    private GameViewController gameViewController = null;

    /**
     * Constructs new instances from sub-controllers.
     */
    private GameView gameView = null;

    private EndGameView endGameView = null;

    private ViewController viewController = null;

    public MainController() {
        viewController = new ViewController(this);
        inputController = new InputController(this);
        inputController.setDaemon(true);
        inputController.start();
        playersController = new PlayersController(this);
        shapesController = new ShapesController(this);
        gameUtilController = new GameUtilController(this);
    }

    public void setGameViewController(final GameViewController gameViewController) {
    	this.gameViewController = gameViewController;
    }

    public GameViewController getGameViewController() {
    	return this.gameViewController;
    }

    /**
     * Gets the used instance to {@link InputController} which allows control over
     * input.
     * @return {@link InputController} instance.
     */
    public InputController getInputController() {
        return this.inputController;
    }

    /**
     * Gets the used instance to {@link PlayersController} which allows control
     * over players.
     * @return {@link PlayersController} instance.
     */
    public PlayersController getPlayersController() {
        return this.playersController;
    }

    /**
     * Gets the used instance to {@link ShapesController} which allows control
     * over shapes.
     * @return {@link ShapesController} instance.
     */
    public ShapesController getShapesController() {
        return this.shapesController;
    }

    /**
     * Gets the used instance to {@link GameUtilController} which allows control
     * over all game utilities (Score, Shelves... etc).
     * @return {@link GameUtilController} instance.
     */
    public GameUtilController getGameUtilController() {
        return this.gameUtilController;
    }

    /**
     * Gets the used instance to {@link ViewController} which allows control over
     * the view (add a node, remove a node, get data about view.. etc).
     * @return {@link ViewController} instance.
     */
    public ViewController getViewController() {
        return viewController;
    }
    /**
     * Sets {@link GameView} to the main controller of the main MVC.
     * @param gameView GameView for the controller to set nodes on.
     */
    public void setGameView(final GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Gets {@link GameView} of the main MVC.
     * @return Currently working {@link GameView}.
     */
    public GameView getGameView() {
        return this.gameView;
    }

    public void setEndGameView(final EndGameView endGameView) {
    	this.endGameView = endGameView;
    }

    public EndGameView getEndGameView() {
    	return this.endGameView;
    }

    /**
     * Starts a new game, calls control to run over view (e.g: render players,
     * create shapes and move them.. etc).
     */
    public void startNewGame() {
        gameUtilController.prepareGame();
        playersController.prepareGame();
        shapesController.startGame();
    }

    /**
     * Displays the end game scene with the score of each player and who won
     * the game, end game scene has 3 options: exit game, return to main menu
     * and start a new game again directly (restart game).
     */
	public void endGame() {

	}
}
