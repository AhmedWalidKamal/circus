package view.gui.mainmenu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.gui.app.Main;
import view.gui.app.util.SceneNavigator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    Pane root;

    @FXML
    Text title;

    /**
     * Scene Navigator objects that allows changing the scene.
     */
    private SceneNavigator sceneNavigator = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            sceneNavigator = new SceneNavigator();
            root.setFocusTraversable(true);
            MainMenuViewHelper.getInstance().configureTheMainMenu(root,title);
            configureNewGameButton();
            configureExitButton();
        }



    private void configureNewGameButton() {
        MainMenuViewHelper.getInstance().getNewGameButton().setOnMouseClicked(event -> {
            try {
                sceneNavigator.changeScene(Main.GAMEVIEW,
                        (Stage) root.getScene().getWindow(),
                        root.getScene().getWidth(),
                        root.getScene().getHeight(),
                        Main.GAMEVIEW_STYLESHEET
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void configureLoadGameButton() {

    }

    private void configureExitButton() {
        MainMenuViewHelper.getInstance().getExitButton().setOnMouseClicked(event -> System.exit(0));
    }

}