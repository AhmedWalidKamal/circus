package view.gui.mainmenu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import view.gui.app.util.SceneNavigator;

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
            MainMenuViewHelper.getInstance().configureTheMainMenu(root,title,sceneNavigator);



        }
    }

