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
        PauseMenuViewHelper.getInstance().configureThePauseMenu(this.pauseMenuPane,this.pauseMenuTitle);
        setVisibilityBindingPauseMenu();
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
		sceneNavigator.loadScreen(Main.ENDGAME_ID, Main.ENDGAME_URL, Main.ENDGAME_STYLESHEET);
		sceneNavigator.setScene(Main.ENDGAME_ID);
	}

	@Override
	public void setScreenParent(final ScenesNavigator screenParent) {
		this.sceneNavigator = screenParent;
		this.mainController = sceneNavigator.getMainController();
		this.mainController.setGameViewController(this);
        this.mainController.setGameView(gameView);
        setKeyBinding();
        final String difficultyLevel = "EASY";
        this.mainController.startNewGame(difficultyLevel);
	}
}
