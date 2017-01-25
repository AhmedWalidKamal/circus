package view.gui.gameplay;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import control.MainController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import logs.LoggingManager;
import model.Player;
import util.Score;
import view.gui.app.Main;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;
import view.gui.endgame.EndGameViewHelper;
import view.gui.mainmenu.MainMenuViewHelper;
import view.gui.mainmenu.util.GameData;
import view.gui.pausemenu.PauseMenuViewHelper;

/**
 * The controller responsible for the game view and
 * the pause and end scenes.
 */
public class GameViewController
implements Initializable, ControlledScenes {
    /**
     * Root pane.
     */
    @FXML
    private AnchorPane root;

    @FXML
    private Pane pauseMenuPane;

    @FXML
    private Text pauseMenuTitle;

    @FXML
    private Pane endGamePane;

    @FXML
    private Text endGameTitle;

    @FXML
    private Text gameWinnerText;

    private ScenesNavigator sceneNavigator;

    private GameData gameData;

    private FileChooser fileChooser = null;

    private static final String TIE_RESULT = "IT'S A TIE!";
    private static final String PLAYER_ONE_WINS = "PLAYER ONE WINS!";
    private static final String PLAYER_TWO_WINS = "PLAYER TWO WINS!";

    /**
     * Instance of {@link MainController} that allows control over both model
     * and view.
     */
    private MainController mainController = null;

    /**
     * Instance of {@link GameView}.
     */
    private GameView gameView = null;

    /**
     * Initialzing the game view and configures pause and
     * end scene buttons.
     */
    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        root.setFocusTraversable(true);
        gameView = new GameView();
        gameView.setRootPane(this.root);
        gameView.setPauseMenuPane(this.pauseMenuPane);
		fileChooser = new FileChooser();
        PauseMenuViewHelper.getInstance().
        configureThePauseMenu(this.pauseMenuPane,
        		this.pauseMenuTitle);
        EndGameViewHelper.getInstance().
        configureEndGameScene(this.endGamePane,
        		this.endGameTitle, this.gameWinnerText);
        setVisibilityBindingPauseMenu();
        setVisibilityBindingEndGame();
        configurePauseMenuButtons();
        configureEndGameButtons();
    }

    /**
     * Starts a new game by sending the data to the main controller
     * and initialzing the initial view of the game.
     * @param gameData
     */
	public void startNewGame(final GameData gameData) {
    	this.gameData = gameData;
    	this.mainController = new MainController();
		this.mainController.setGameViewController(this);
        this.mainController.setGameView(gameView);
        setKeyBinding();
        this.mainController.startNewGame(this.gameData);
    }

	public void loadGame(final String path) throws IOException {
		this.mainController = new MainController();
		this.mainController.setGameViewController(this);
		this.mainController.setGameView(gameView);
		setKeyBinding();
		this.mainController.loadGame(path);
	}

	/**
	 * Binds the pane's visibility with the managed property.
	 */
    private void setVisibilityBindingEndGame() {
		endGamePane.managedProperty().bind(endGamePane.visibleProperty());
		endGamePane.setVisible(false);
	}

	/**
     * Adds a key input handler to a root pane to send the entered
     * {@link javafx.scene.input.KeyCode} to {@link control.InputController}.
     */
    private void setKeyBinding() {
        root.setOnKeyPressed(event -> mainController.getInputController()
                .executeKeyCommand(event.getCode(), true));
        root.setOnKeyReleased(event -> mainController.getInputController()
                .executeKeyCommand(event.getCode(), false));
    }

    /**
	 * Binds the pane's visibility with the managed property.
	 */
    private void setVisibilityBindingPauseMenu() {
        pauseMenuPane.managedProperty().bind(pauseMenuPane.visibleProperty());
        pauseMenuPane.setVisible(false);
    }

    public GameView getGameView() {
        return this.gameView;
    }

    /**
     * Displays the end game scene.
     */
	public void showEndGameScene() {
		LoggingManager.getInstance().info("GAME ENDED");
		this.mainController.pause();
		determineWinner();
        this.endGamePane.setVisible(true);
        this.endGamePane.toFront();
        this.endGamePane.requestFocus();
	}

	/**
	 * Determines the message to be displayed according to
	 * who scored more points.
	 */
	private void determineWinner() {
		Score playerOneScore = new Score();
		Score playerTwoScore = new Score();
		boolean firstPlayer = true;
		for (Player player : mainController.getPlayersController().getPlayers()) {
			if (firstPlayer) {
				playerOneScore = player.getScore();
				firstPlayer = false;
			} else {
				playerTwoScore = player.getScore();
			}
		}
		if (playerOneScore.compareTo(playerTwoScore) == 0) {
			gameWinnerText.setText(TIE_RESULT);
			LoggingManager.getInstance().info(TIE_RESULT);
		} else if (playerOneScore.compareTo(playerTwoScore) > 0) {
			gameWinnerText.setText(PLAYER_ONE_WINS);
			LoggingManager.getInstance().info(PLAYER_ONE_WINS);
		} else {
			gameWinnerText.setText(PLAYER_TWO_WINS);
			LoggingManager.getInstance().info(PLAYER_TWO_WINS);
		}
	}

	/**
	 * Configures the end game scene buttons' actions.
	 */
	private void configureEndGameButtons() {
	    EndGameViewHelper.getInstance().getRestartGameButton().setOnMouseClicked(event -> {
	    	this.sceneNavigator.startGame(Main.GAMEVIEW_ID,
	    			Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET, this.gameData);
	    	this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
			LoggingManager.getInstance().info("GAME RESTARTED");
        });
	    EndGameViewHelper.getInstance().getReturnToMenuMainButton().setOnMouseClicked(event -> {
        	this.sceneNavigator.setScene(Main.MAINMENU_ID);
			LoggingManager.getInstance().info("RETURNED TO MAIN MENU");
        });
	    EndGameViewHelper.getInstance().getExitGameButton().setOnMouseClicked(event -> {
        	System.exit(0);
			LoggingManager.getInstance().info("GAME EXITED");
        });
	}

	/**
	 * Configures the pause menu buttons' actions.
	 */
    private void configurePauseMenuButtons() {
    	PauseMenuViewHelper.
    	getInstance().getResumeButton().
    	setOnMouseClicked(event -> {
    		this.pauseMenuPane.setVisible(false);
            this.pauseMenuPane.toBack();
            this.root.requestFocus();
            mainController.resume();
			LoggingManager.getInstance().info("GAME RESUMED");
    	});

    	PauseMenuViewHelper.
    	getInstance().getOptionsButton().
    	setOnMouseClicked(event -> {
			LoggingManager.getInstance().info("GAME OPTIONS CONFIGURED");
    	});

    	PauseMenuViewHelper.
    	getInstance().getReturnToMenuMainButton().
    	setOnMouseClicked(event -> {
			MainMenuViewHelper.getInstance().getMainMenuPane().lookup("#mainMenu").setDisable(false);
    		this.sceneNavigator.setScene(Main.MAINMENU_ID);
			LoggingManager.getInstance().info("RETURNED TO MAIN MENU");
    	});

    	PauseMenuViewHelper.
    	getInstance().getSaveButton().
    	setOnMouseClicked(event -> {
			Platform.runLater(() -> {
				FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
						"PROTOCOL BUFFER files(*.protobuff)","*.protobuff");
				fileChooser.getExtensionFilters().addAll(extensionFilter);
				File file = fileChooser.showSaveDialog(null);
				try {
					mainController.saveGame(file.getPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			LoggingManager.getInstance().info("GAME SAVED");
    	});
	}

	@Override
	public void setScreenParent(final ScenesNavigator screenParent) {
		this.sceneNavigator = screenParent;
	}
}
