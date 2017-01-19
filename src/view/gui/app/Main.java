package view.gui.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.gui.app.util.SceneNavigator;
import view.gui.mainview.MainViewController;

public class Main extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        //final Pane root = FXMLLoader.load(getClass().getResource(
          //      "/view/gui/mainmenu/MainMenuFxml.fxml"));
    	//root.getStylesheets().add(this.getClass().getResource("/view/gui/gameplay/GameViewStyle.css").toExternalForm());
        primaryStage.setTitle("Circus");
        primaryStage.setScene(createScene(loadMainPane()));
        //root.prefWidthProperty().bind(primaryStage.getScene().widthProperty());
        //root.prefHeightProperty().bind(primaryStage.getScene().heightProperty());
        primaryStage.show();
        //root.requestFocus();
        //root.toFront();
    }
    private Scene createScene(final Pane mainPane) {
		final Scene scene = new Scene(mainPane);
//		 scene.getStylesheets().setAll(
//		            getClass().getResource("vista.css").
//		            toExternalForm());
		return scene;
	}
	private Pane loadMainPane() throws IOException {
		final FXMLLoader loader = new FXMLLoader();
        final Pane mainPane = loader.load(
            getClass().getResourceAsStream(
                SceneNavigator.MAINVIEW));
        final MainViewController mainViewController = loader.getController();
        SceneNavigator.setMainViewController(mainViewController);
        SceneNavigator.loadPane(SceneNavigator.MAINMENU);
        return mainPane;
	}
	public static final void main(final String[] args) {
        launch(args);
    }
}
