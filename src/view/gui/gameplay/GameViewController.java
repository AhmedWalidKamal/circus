package view.gui.gameplay;


import behaviour.keyBinding.AKeyHandler;
import behaviour.keyBinding.KeyMap;
import behaviour.keyBinding.LeftArrowKeyHandler;
import controller.MainController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {
    /**
     * Root pane.
     */
    @FXML private AnchorPane root;

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
        mainController = new MainController();
        gameView = new GameView();
        gameView.setRootPane(this.root);
        mainController.setGameView(gameView);
        setKeyBinding();
        test();
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

    private void test() {
        Rectangle rect = new Rectangle(50, 50);
        rect.setX(500);
        rect.setY(100);
        KeyMap keyMap = new KeyMap(rect);
        keyMap.addKeyHandler(new LeftArrowKeyHandler());
        mainController.getInputController().addKeyMap(keyMap);
        root.getChildren().add(rect);

        Rectangle rect2 = new Rectangle(50, 50);
        rect2.setX(500);
        rect2.setY(200);
        KeyMap keyMap1 = new KeyMap(rect2);
        keyMap1.addKeyHandler(new AKeyHandler());
        mainController.getInputController().addKeyMap(keyMap1);
        root.getChildren().add(rect2);
    }
}
