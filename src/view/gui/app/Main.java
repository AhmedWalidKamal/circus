package view.gui.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.gui.app.util.ScenesNavigator;

public class Main extends Application {


	public static final String MAINMENU_URL = "/view/gui/mainmenu/MainMenuFxml.fxml";
	public static final String MAINMENU_STYLESHEET = "/view/gui/mainmenu/MainMenuStyle.css";
	public static final String MAINMENU_ID = "MAINMENU";
	public static final String GAMEVIEW_URL = "/view/gui/gameplay/GameViewFxml.fxml";
	public static final String GAMEVIEW_STYLESHEET = "/view/gui/gameplay/GameViewStyle.css";
	public static final String GAMEVIEW_ID = "GAMEVIEW";
	public static final String ENDGAME_ID = "ENDGAME";
	public static final String ENDGAME_URL = "/view/gui/endgame/EndGameFxml.fxml";
	public static final String ENDGAME_STYLESHEET = "/view/gui/endgame/EndGameStyle.css";

    @Override
    public final void start(final Stage primaryStage) throws Exception {
    	final ScenesNavigator screensController = new ScenesNavigator();
    	screensController.loadScreen(MAINMENU_ID, MAINMENU_URL, MAINMENU_STYLESHEET);
    	screensController.setScreen(MAINMENU_ID);
    	final Group root = new Group();
    	root.getChildren().addAll(screensController);
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
        root.toFront();
    }

 	public static final void main(final String[] args) {
        launch(args);
    }
}
