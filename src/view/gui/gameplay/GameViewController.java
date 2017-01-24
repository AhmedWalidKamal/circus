package view.gui.gameplay;


import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import view.gui.app.Main;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;
import view.gui.endgame.EndGameViewHelper;
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

    private ScenesNavigator sceneNavigator;

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
        this.mainController = new MainController();
		this.mainController.setGameViewController(this);
        this.mainController.setGameView(gameView);
        setKeyBinding();
        final String difficultyLevel = "EASY";
        this.mainController.startNewGame(difficultyLevel);
        PauseMenuViewHelper.getInstance().configureThePauseMenu(this.pauseMenuPane,this.pauseMenuTitle);
        EndGameViewHelper.getInstance().configureEndGameScene(this.endGamePane, this.endGameTitle);
        setVisibilityBindingPauseMenu();
        setVisibilityBindingEndGame();
        configureExitGameButton();
        configureRestartGameButton();
        configureReturnToMainMenuButton();
    }

    private void setVisibilityBindingEndGame() {
		endGamePane.managedProperty().bind(endGamePane.visibleProperty());
		endGamePane.setVisible(false);
	}

	/**
     * Adds a key input handler to a root pane to send the entered
     * {@link javafx.scene.input.KeyCode} to {@link controller.InputController}.
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
        this.endGamePane.setVisible(true);
        this.endGamePane.toFront();
        this.endGamePane.requestFocus();
	}

	private void configureExitGameButton() {
        EndGameViewHelper.getInstance().getExitGameButton().setOnMouseClicked(event -> {
        	System.exit(0);
        });
    }

	private void configureReturnToMainMenuButton() {
        EndGameViewHelper.getInstance().getReturnToMenuMainButton().setOnMouseClicked(event -> {
        	this.sceneNavigator.setScene(Main.MAINMENU_ID);
        });
    }

	private void configureRestartGameButton() {
	    EndGameViewHelper.getInstance().getRestartGameButton().setOnMouseClicked(event -> {
	    	this.sceneNavigator.loadScene(Main.GAMEVIEW_ID,
	    			Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET);
	    	this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
        });
	}
	@Override
	public void setScreenParent(final ScenesNavigator screenParent) {
		this.sceneNavigator = screenParent;
	}
}
