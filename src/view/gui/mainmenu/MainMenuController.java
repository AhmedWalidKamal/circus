package view.gui.mainmenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.gui.app.Main;
import view.gui.app.util.SceneNavigator;

public class MainMenuController implements Initializable {
	/**
     * Root pane.
     */
    @FXML
    private StackPane root;

    /**
     * Button to start a new game.
     */
    @FXML
    private Button newGameButton;

    /**
     * Scene Navigator objects that allows changing the scene.
     */
    private SceneNavigator sceneNavigator = null;

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        root.setFocusTraversable(true);
        sceneNavigator = new SceneNavigator();
    }

    @FXML
    private void openGameView(final ActionEvent event) throws IOException {
    	sceneNavigator.changeScene(Main.GAMEVIEW,
    			(Stage) this.newGameButton.getScene().getWindow(),
    			this.newGameButton.getScene().getWidth(),
    			this.newGameButton.getScene().getHeight()
    			);
    }

    @FXML
    private void exitGame(final ActionEvent event) {
    	System.exit(0);
    }
}
