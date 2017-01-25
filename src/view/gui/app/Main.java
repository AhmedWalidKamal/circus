package view.gui.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import logs.LoggingManager;
import view.gui.app.util.ScenesNavigator;

import java.io.File;

public class Main extends Application {


	public static final String MAINMENU_URL = "/view/gui/mainmenu/MainMenuFxml.fxml";
	public static final String MAINMENU_STYLESHEET = "/view/gui/mainmenu/MainMenuStyle.css";
	public static final String MAINMENU_ID = "MAINMENU";
	public static final String GAMEVIEW_URL = "/view/gui/gameplay/GameViewFxml.fxml";
	public static final String GAMEVIEW_STYLESHEET = "/view/gui/gameplay/GameViewStyle.css";
	public static final String GAMEVIEW_ID = "GAMEVIEW";

    @Override
    public final void start(final Stage primaryStage) throws Exception {
    	final ScenesNavigator screensController = new ScenesNavigator();
    	screensController.loadScene(MAINMENU_ID, MAINMENU_URL, MAINMENU_STYLESHEET);
    	screensController.setScene(MAINMENU_ID);
    	final Group root = new Group();
    	root.getChildren().addAll(screensController);
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
        root.toFront();
//		String path = "C:\\Users\\Samsung\\git\\circus-of-plates\\lib\\backgroundMusic.mp3";
//		Media media = new Media(new File(path).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.setAutoPlay(true);
//		MediaView mediaView = new MediaView(mediaPlayer);
		LoggingManager.getInstance().info("GAME OPENED");
    }

 	public static final void main(final String[] args) {
        launch(args);
    }
}
