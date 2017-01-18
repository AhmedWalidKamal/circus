package view.gui.mainmenu;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import view.gui.app.util.SceneNavigator;

public class MainMenuController implements Initializable {
	/**
     * Root pane.
     */
    @FXML
    private AnchorPane root;

    @FXML
    private Button newGameButton;

    /**
     * Instance of {@link MainController} that allows control over both model
     * and view.
     */
    private MainController mainController = null;

    /**
     * Instance of {@link MainMenu}.
     */
    private MainMenu mainMenu = null;

    @FXML
    private void openGameView() {
    	SceneNavigator.loadPane(SceneNavigator.GAMEVIEW);
    }
    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        root.setFocusTraversable(true);
        mainController = new MainController();
        mainMenu = new MainMenu();
        mainMenu.setRootPane(this.root);
        mainController.setMainMenu(mainMenu);
        mainController.startNewGame();
    }

    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}
