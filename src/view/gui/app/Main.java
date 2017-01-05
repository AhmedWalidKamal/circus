package view.gui.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public final void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource(
                "/view/gui/gameplay/GameViewFxml.fxml"));
        primaryStage.setTitle("Circus");
        primaryStage.setScene(new Scene(root, 1280, 1024));
        root.prefWidthProperty().bind(primaryStage.getScene().widthProperty());
        primaryStage.show();
        root.requestFocus();
        root.toFront();
    }
    public static final void main(String[] args) {
        launch(args);
    }
}
