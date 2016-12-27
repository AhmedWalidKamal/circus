package view.gui.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public final void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource(
                "/view/gui/gameplay/GameViewFxml.fxml"));
        ImageView background = new ImageView("file:src/assets/game_background.png");
        background.fitHeightProperty().bind(root.heightProperty());
        background.fitWidthProperty().bind(root.widthProperty());
        root.getChildren().add(background);
        primaryStage.setTitle("Circus");
        primaryStage.setScene(new Scene(root, 1024, 800));
        primaryStage.show();
    }

    public static final void main(String[] args) {
        launch(args);
    }
}
