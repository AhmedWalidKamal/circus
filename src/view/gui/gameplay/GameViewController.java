package view.gui.gameplay;


import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.shapes.Shape;

public class GameViewController implements Initializable {
    /**
     * Root pane.
     */
    @FXML
    private AnchorPane root;

    /**
     * Instance of {@link MainController} that allows control over both model
     * and view.
     */
    private MainController mainController = null;

    /**
     * Instance of {@link GameView}.
     */
    private GameView gameView = null;

    private Shape shape1;
    private Shape shape2;
    private Shape shape3;

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        root.setFocusTraversable(true);
        mainController = new MainController();
        gameView = new GameView();
        gameView.setRootPane(this.root);
        mainController.setGameView(gameView);
        setKeyBinding();
        String difficultyLevel="HARD";
        mainController.startNewGame(difficultyLevel);
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

    public GameView getGameView() {
        return this.gameView;
    }
}
