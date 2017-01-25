package view.gui.gameplay;


import java.net.URL;
import java.util.ResourceBundle;

import control.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Player;
import util.Score;
import view.gui.app.Main;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;
import view.gui.endgame.EndGameViewHelper;
import view.gui.mainmenu.MainMenuViewHelper;
import view.gui.mainmenu.util.GameData;
import view.gui.pausemenu.PauseMenuViewHelper;

public class GameViewController implements Initializable, ControlledScenes {
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

    /**
     * Instance of {@link MainController} that allows control over both model
     * and view.
     */
    private MainController mainController = null;

    /**
     * Instance of {@link GameView}.
     */
    private GameView gameView = null;

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        root.setFocusTraversable(true);
        gameView = new GameView();
        gameView.setRootPane(this.root);
        gameView.setPauseMenuPane(this.pauseMenuPane);

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

	public void startNewGame(final GameData gameData) {
    	this.gameData = gameData;
    	this.mainController = new MainController();
		this.mainController.setGameViewController(this);
        this.mainController.setGameView(gameView);
        setKeyBinding();
        this.mainController.startNewGame(this.gameData);
    }
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

    private void setVisibilityBindingPauseMenu() {
        pauseMenuPane.managedProperty().bind(pauseMenuPane.visibleProperty());
        pauseMenuPane.setVisible(false);
    }

    public GameView getGameView() {
        return this.gameView;
    }

	public void showEndGameScene() {
		this.mainController.pause();
		determineWinner();
        this.endGamePane.setVisible(true);
        this.endGamePane.toFront();
        this.endGamePane.requestFocus();
	}

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
			gameWinnerText.setText("IT'S A TIE!");
		} else if (playerOneScore.compareTo(playerTwoScore) > 0) {
			gameWinnerText.setText("PLAYER ONE WINS!");
		} else {
			gameWinnerText.setText("PLAYER TWO WINS!");
		}
		gameWinnerText.setOpacity(1.0);
	}

	private void configureEndGameButtons() {
	    EndGameViewHelper.getInstance().getRestartGameButton().setOnMouseClicked(event -> {
	    	this.sceneNavigator.loadGame(Main.GAMEVIEW_ID,
	    			Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET, this.gameData);
	    	this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
        });
	    EndGameViewHelper.getInstance().getReturnToMenuMainButton().setOnMouseClicked(event -> {
        	this.sceneNavigator.setScene(Main.MAINMENU_ID);
        });
	    EndGameViewHelper.getInstance().getExitGameButton().setOnMouseClicked(event -> {
        	System.exit(0);
        });
	}

    private void configurePauseMenuButtons() {
    	PauseMenuViewHelper.
    	getInstance().getResumeButton().
    	setOnMouseClicked(event -> {
    		this.pauseMenuPane.setVisible(false);
            this.pauseMenuPane.toBack();
            this.root.requestFocus();
            mainController.resume();
    	});

    	PauseMenuViewHelper.
    	getInstance().getOptionsButton().
    	setOnMouseClicked(event -> {

    	});

    	PauseMenuViewHelper.
    	getInstance().getReturnToMenuMainButton().
    	setOnMouseClicked(event -> {
			MainMenuViewHelper.getInstance().getMainMenuPane().lookup("#mainMenu").setDisable(false);
    		this.sceneNavigator.setScene(Main.MAINMENU_ID);
    	});

    	PauseMenuViewHelper.
    	getInstance().getSaveButton().
    	setOnMouseClicked(event -> {

    	});
	}

	@Override
	public void setScreenParent(final ScenesNavigator screenParent) {
		this.sceneNavigator = screenParent;
	}
}
