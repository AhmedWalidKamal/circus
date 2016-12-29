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
        final Rectangle rectPath = new Rectangle (10, 10, 40, 40);
        rectPath.setFill(Color.BLUE);
        root.getChildren().add(rectPath);
        Path path = new Path();
        path.getElements().add(new MoveTo(50,50));
        path.getElements().add(new LineTo(50,50));
        path.getElements().add(new LineTo(200,50));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
       // pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
       // pathTransition.setCycleCount(Timeline.INDEFINITE);
        //pathTransition.setAutoReverse(true);
        pathTransition.play();
        long prevTime = System.nanoTime();
        while(rectPath.getX()<1024&&rectPath.getY()<800){
            Path path1 = new Path();
            long currentTime = System.nanoTime();
            double dt = 3 * (currentTime - prevTime) / 1e8;
           //change x and y coor according to time
            double x = rectPath.getX() + dt * rectPath.getX();
            double y = rectPath.getY() + dt * rectPath.getY();
            prevTime = currentTime;
            path1.getElements().add(new MoveTo(rectPath.getX(),rectPath.getY()));
            path1.getElements().add(new LineTo(x,y));
            PathTransition pathTransition1 = new PathTransition();
            pathTransition1.setDuration(Duration.millis(4000));
            pathTransition1.setPath(path);
            pathTransition1.setNode(rectPath);
            pathTransition1.play();
        }
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
