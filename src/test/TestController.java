package test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TestController implements Initializable {
    @FXML
    private AnchorPane root;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final Rectangle rect = new Rectangle(50, 50);
        //rect.setFill(Color.BROWN);
		rect.setFill(Paint.valueOf("WHITE"));
        rect.setTranslateX(50);
        rect.setTranslateY(50);
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                final FadeTransition fadeTransition =
                        new FadeTransition(Duration.millis(3000), rect);
                fadeTransition.setFromValue(1.0f);
                fadeTransition.setToValue(0.3f);
                fadeTransition.setCycleCount(2);
                fadeTransition.setAutoReverse(true);
                final TranslateTransition translateTransition =
                        new TranslateTransition(Duration.millis(2000), rect);
                translateTransition.setFromX(50);
                translateTransition.setToX(350);
                translateTransition.setCycleCount(2);
                translateTransition.setAutoReverse(true);
                final RotateTransition rotateTransition =
                        new RotateTransition(Duration.millis(3000), rect);
                rotateTransition.setByAngle(180f);
                rotateTransition.setCycleCount(4);
                rotateTransition.setAutoReverse(true);
                final ScaleTransition scaleTransition =
                        new ScaleTransition(Duration.millis(2000), rect);
                scaleTransition.setToX(2f);
                scaleTransition.setToY(2f);
                scaleTransition.setCycleCount(2);
                scaleTransition.setAutoReverse(true);

                final ParallelTransition parallelTransition = new ParallelTransition();
                parallelTransition.getChildren().addAll(
                        fadeTransition,
                        translateTransition,
                        rotateTransition,
                        scaleTransition
                );
                parallelTransition.setCycleCount(Timeline.INDEFINITE);
                parallelTransition.play();
            }
        });
        root.getChildren().add(rect);
    }
}