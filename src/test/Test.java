package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("TestFxml.fxml"));
//        ImageView background = new ImageView("file:src/assets/game_background.png");
//        background.fitHeightProperty().bind(root.heightProperty());
//        background.fitWidthProperty().bind(root.widthProperty());
//        background.toBack();
//        root.getChildren().add(background);
        primaryStage.setTitle("Circus");
        primaryStage.setScene(new Scene(root, 1024, 800));
        primaryStage.show();
        root.requestFocus();
    }
}
