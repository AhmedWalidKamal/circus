package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException; 

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("TestFxml.fxml"));
//        ImageView background = new ImageView(new Image("File:src/assets/game_background.img", 1024, 800, false, true));
//        background.fitHeightProperty().bind(root.heightProperty());
//        background.fitWidthProperty().bind(root.widthProperty());
//        root.getChildren().add(background);

//        root.setStyle(
//                "-fx-background-image: url(\"File:src/assets/game_background"
//                        + ".png\");\n"
//                        + "    -fx-background-size: stretch;"
//        );

        primaryStage.setTitle("Circus");
        primaryStage.setScene(new Scene(root, 1024, 800));
        primaryStage.show();
        root.requestFocus();
        root.setFocusTraversable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
