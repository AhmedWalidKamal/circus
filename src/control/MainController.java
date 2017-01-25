package control;

import model.save.ModelMemento;
import view.gui.gameplay.GameView;
import view.gui.gameplay.GameViewController;
import view.gui.mainmenu.util.GameData;

import java.io.IOException;

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
     * {@link LevelsController} instance.
     */
    private LevelsController levelsController = null;

    private FileController fileController = null;

    /**
     * Constructs new instances from sub-controllers.
     */
    private GameView gameView = null;

    private ViewController viewController = null;

    public MainController() {
        viewController = new ViewController(this);
        inputController = new InputController(this);
        inputController.setDaemon(true);
        playersController = new PlayersController(this);
        shapesController = new ShapesController(this);
        shapesController.setDaemon(true);
        gameUtilController = new GameUtilController(this);
        levelsController = new LevelsController(this);
        fileController = new FileController(this);
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
     * Gets the used instance to {@link LevelsController} which allows control over
     * different game levels.
     * @return {@link LevelsController} instance.
     */
    public LevelsController getLevelsController() {
        return this.levelsController;
    }

    /**
    /**
     * Gets the used instance to {@link ViewController} which allows control over
     * the view (add a node, remove a node, get data about view.. etc).
     * @return {@link ViewController} instance.
     */
    public ViewController getViewController() {
        return viewController;
    }
    /**
     * Sets {@link GameView} to the main control of the main MVC.
     * @param gameView GameView for the control to set nodes on.
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

    public FileController getFileController() {
        return fileController;
    }

    /**
     * Starts a new game, calls control to run over view (e.g: render players,
     * create shapes and move them.. etc).
     */
    public void startNewGame(final GameData gameData) {
        levelsController.chooseLevel(gameData.getGameDifficulty());
        playersController.prepareGame();
        prepareGame();
    }

    public void pause() {
        inputController.pauseThread();
        shapesController.pauseThread();
        gameUtilController.pauseTime();
    }

    public void resume() {
        inputController.resumeThread();
        shapesController.resumeThread();
        gameUtilController.resumeTime();
    }

    public void saveGame(final String path) throws IOException {
        ModelMemento memento = new ModelMemento();
        playersController.collectMemento(memento);
        gameUtilController.collectMemento(memento);
        levelsController.collectMemento(memento);
        fileController.save(memento, path);
    }

	public void loadGame(final String path) throws IOException {
        ModelMemento memento = fileController.load(path);
        playersController.loadFromMemento(memento);
        shapesController.loadFromMemento(memento);
        gameUtilController.loadFromMemento(memento);
        levelsController.loadFromMemento(memento);
        prepareGame();
	}

    private void prepareGame() {
        gameUtilController.prepareGame();
        inputController.start();
        shapesController.start();
    }
}
