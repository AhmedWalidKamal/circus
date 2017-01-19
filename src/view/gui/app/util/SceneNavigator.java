package view.gui.app.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneNavigator {

	public void changeScene(final String fxml,
			final Stage primaryStage, final double width,
			final double height) throws IOException {
		final Pane root = FXMLLoader.load(getClass().getResource(fxml));
		primaryStage.setScene(new Scene(root, width, height));
        root.prefWidthProperty().bind(primaryStage.getScene().widthProperty());
        root.prefHeightProperty().bind(primaryStage.getScene().heightProperty());
        primaryStage.show();
        root.requestFocus();
        root.toFront();
	}
}
