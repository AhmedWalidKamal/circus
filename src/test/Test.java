package test;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test extends Application {

	private static final Integer STARTTIME = 60;
    private Timeline timeline;
    private final Label timerLabel = new Label();
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    @Override
    public void start(final Stage primaryStage) throws IOException {
        final Pane root = FXMLLoader.load(getClass().getResource("TestFxml.fxml"));
//        ImageView background = new ImageView(new Image("File:src/assets/game_background.img", 1024, 800, false, true));
//        background.fitHeightProperty().bind(root.heightProperty());
//        background.fitWidthProperty().bind(root.widthProperty());
//        root.getChildren().add(background);

//        root.setStyle(
//                "-fx-background-image: url(\"File:src/assets/game_background"
//                        + ".png\");\n"
//                        + "    -fx-background-size: stretch;"
//        );
//        ///demo
//        final Rectangle rectPath = new Rectangle (10, 10, 40, 40);
//        rectPath.setFill(Color.BLUE);
//        root.getChildren().add(rectPath);
//        final Path path = new Path();
//        path.getElements().add(new MoveTo(50,50));
//        path.getElements().add(new LineTo(200,50));
//        rectPath.setX(200);
//        rectPath.setY(50);
//        long prevTime = System.nanoTime();
//        int count = 0;
//        double factor = 0.8;
//        while (rectPath.getX() < 1024 && rectPath.getY() < 800){
//            final long currentTime = System.nanoTime();
//            final double dt = 3 * (currentTime - prevTime) / 1e8;
//            //change x and y coor according to time
//            final double x = rectPath.getX() + dt * rectPath.getX();
//            final double y = rectPath.getY() + 2 * dt * 0.98 * rectPath.getY();
//            factor += 0.03;
//            prevTime = currentTime;
//            path.getElements().add(new LineTo(x, y));
//            final PathTransition pathTransition = new PathTransition();
//            pathTransition.setDuration(Duration.seconds(8));
//            pathTransition.setPath(path);
//            pathTransition.setNode(rectPath);
//            pathTransition.play();
//            rectPath.setX(x);
//            rectPath.setY(y);
//            count++;
//        }
//        System.out.println(count);

        primaryStage.setTitle("Circus");
        primaryStage.setScene(new Scene(root, 1024, 800));

        // Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 1),
                new KeyValue(timeSeconds, 0)));
        timeline.play();

        final VBox vb = new VBox(20);             // gap between components is 20
        vb.setAlignment(Pos.CENTER);        // center the components within VBox
        vb.setPrefWidth(primaryStage.getScene().getWidth());
        vb.getChildren().add(timerLabel);
        vb.setLayoutY(30);
        root.getChildren().add(vb);

        primaryStage.show();
        root.requestFocus();
        root.setFocusTraversable(true);

    }

    public static void main(final String[] args) {
        launch(args);
    }
}