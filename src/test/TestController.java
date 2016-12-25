package test;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    private AnchorPane root;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        Rectangle rect = new Rectangle(50, 50);
        rect.setFill(Color.BROWN);
        rect.setTranslateX(50);
        rect.setTranslateY(50);
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                TranslateTransition translate = new TranslateTransition(
                        Duration.millis(5000), rect);
                translate.setFromY(50);
                translate.toYProperty().bind(root.heightProperty());
                translate.setCycleCount(Timeline.INDEFINITE);
                translate.setAutoReverse(true);
                translate.play();
            }
        });
        root.getChildren().add(rect);
    }
}
