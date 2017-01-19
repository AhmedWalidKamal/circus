package view.gui.app.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import view.gui.mainview.MainViewController;

public class SceneNavigator {

	public static final String MAINMENU = "/view/gui/mainmenu/MainMenuFxml.fxml";
	public static final String GAMEVIEW = "/view/gui/gameplay/GameViewFxml.fxml";
	public static final String MAINVIEW = "/view/gui/mainview/MainViewFxml.fxml";

	private static MainViewController mainViewController;

	public static void setMainViewController(final MainViewController mainViewController) {
		SceneNavigator.mainViewController = mainViewController;
	}

	public static void loadPane(final String fxml) {
		try {
			mainViewController.setCurrentPane(FXMLLoader.
					load(SceneNavigator.class.getResource(fxml)));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
