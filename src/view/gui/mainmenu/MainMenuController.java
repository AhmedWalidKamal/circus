package view.gui.mainmenu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import view.gui.app.Main;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;

public class MainMenuController implements Initializable, ControlledScenes {

    @FXML
    private Pane root;

    @FXML
    private Text title;

    private ScenesNavigator myController;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
            root.setFocusTraversable(true);
            MainMenuViewHelper.getInstance().setMainMenuController(this);
            MainMenuViewHelper.getInstance().configureTheMainMenu(root, title);
    }

	@Override
	public void setScreenParent(final ScenesNavigator screenParent) {
		this.myController = screenParent;
	}

	public void loadGameScene() {
		this.myController.loadScreen(Main.GAMEVIEW_ID, Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET);
		this.myController.setScreen(Main.GAMEVIEW_ID);
	}
}
