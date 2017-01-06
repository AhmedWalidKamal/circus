package test;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.CORBA.TIMEOUT;

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
        ///demo
       /* final Rectangle rectPath = new Rectangle (10, 10, 40, 40);
        rectPath.setFill(Color.BLUE);
        root.getChildren().add(rectPath);
        Path path = new Path();
        path.getElements().add(new MoveTo(50,50));
        path.getElements().add(new LineTo(200,50));
        rectPath.setX(200);
        rectPath.setY(50);
        long prevTime = System.nanoTime();
        int count = 0;
        double factor = 0.8;
        while (rectPath.getX() < 1024 && rectPath.getY() < 800){
            long currentTime = System.nanoTime();
            double dt = 3 * (currentTime - prevTime) / 1e8;
            //change x and y coor according to time
            double x = rectPath.getX() + dt * rectPath.getX();
            double y = rectPath.getY() + 2 * dt * 0.98 * rectPath.getY();
            factor += 0.03;
            prevTime = currentTime;
            path.getElements().add(new LineTo(x, y));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(8));
            pathTransition.setPath(path);
            pathTransition.setNode(rectPath);
            pathTransition.play();
            rectPath.setX(x);
            rectPath.setY(y);
            count++;
        }
        System.out.println(count);
        primaryStage.setTitle("Circus");
        primaryStage.setScene(new Scene(root, 1024, 800));
        primaryStage.show();
        root.requestFocus();
        root.setFocusTraversable(true);*/

    }

    public static void main(String[] args) {
        launch(args);
    }
}