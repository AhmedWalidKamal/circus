package view.gui.gameplay;


import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.shapes.Shape;

public class GameViewController implements Initializable, Observer {
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
        mainController = new MainController(this);
        gameView = new GameView();
        gameView.setRootPane(this.root);
        mainController.setGameView(gameView);
        setKeyBinding();
        mainController.startNewGame();
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
    public void setShape1(Shape shape1){
        this.shape1=shape1;
    }
    public void setShape2(Shape shape2){
        this.shape2=shape2;
    }
    public void setShape3(Shape shape3) { this.shape3=shape3; }
	@Override
	public void update(final Observable arg0, final Object arg1) {
        Platform.runLater(() -> {
            this.mainController.getGameView().
                    getRootPane().getChildren().remove(shape1.getImageView());
            shape1=null;
            this.mainController.getGameView().
                    getRootPane().getChildren().remove(shape2.getImageView());
            shape2=null;
            this.mainController.getGameView().
                    getRootPane().getChildren().remove(shape3.getImageView());
            shape3=null;
        });
	}
}
