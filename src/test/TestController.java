package test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TestController implements Initializable {
    @FXML
    private AnchorPane root;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
//        Rectangle rect1 = new Rectangle(50, 50);
//        rect1.setFill(Color.BROWN);
//        rect1.setTranslateX(50);
//        rect1.setTranslateY(50);
//        Rectangle rect2 = new Rectangle(50, 50);
//        rect2.setFill(Color.CHOCOLATE);
//        rect2.setTranslateX(150);
//        rect2.setTranslateY(50);
//
//        TranslateTransition rect1Transition = new TranslateTransition
//                (Duration.millis(5000), rect1);
//        rect1Transition.setFromY(50);
//        rect1Transition.setToY(1000);
//        rect1Transition.setCycleCount(Timeline.INDEFINITE);
//        rect1Transition.setAutoReverse(true);
//
//        TranslateTransition rect2Transition = new TranslateTransition
//                (Duration.millis(5000), rect2);
//        rect2Transition.setFromY(50);
//        rect2Transition.setToY(1000);
//        rect2Transition.setCycleCount(Timeline.INDEFINITE);
//        rect2Transition.setAutoReverse(true);
//
//        Thread t1 = new Thread("Move rect1") {
//            @Override
//            public void run() {
//                synchronized (rect1Transition) {
//                    try {
//                        rect1Transition.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        Thread t2 = new Thread("Move rect2") {
//            @Override
//            public void run() {
//                synchronized (rect1Transition) {
//                    rect1Transition.notify();
//                }
//                rect1Transition.play();
//                rect2Transition.play();
//            }
//        };
//
//        root.getChildren().add(rect1);
//        root.getChildren().add(rect2);
//        t1.start();
//        t2.start();
//
//        BooleanBinding binding = new SimpleBooleanProperty(false)
//                .and(new SimpleBooleanProperty(true));
//        binding.addListener((observable, oldValue, newValue) -> {
//        });

        final ImageView img = new ImageView("File:src/assets/Blue.png");
        System.out.print(img.getImage().getWidth());
//        img.setTranslateX(100);
//        img.setTranslateY(100);
//        img.setPreserveRatio(true);
//        img.setFitWidth(150);
//        img.setPreserveRatio(true);
//        img.setSmooth(true);
//        img.setCache(true);
//        TranslateTransition transition = new TranslateTransition(Duration.millis(5000), img);
//        transition.setFromY(100);
//        transition.setToY(1000);
//        transition.setCycleCount(3);
//        transition.setAutoReverse(true);
//        root.getChildren().add(img);
//        transition.play();
    }
}