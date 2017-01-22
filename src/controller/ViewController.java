package controller;

import javafx.scene.Node;
import model.Shelf;

public class ViewController {
    private MainController mainController = null;

    public ViewController(MainController mainController) {
        this.mainController = mainController;
    }

    public double getRootPanePrefWidth() {
        return mainController.getGameView().getRootPane().prefWidthProperty()
                .doubleValue();
    }

    public double getRootPanePrefHeight() {
        return mainController.getGameView().getRootPane().prefHeightProperty()
                .doubleValue();
    }

    public void addToRootPane(Node node) {
        mainController.getGameView().getRootPane().getChildren().add(node);
    }

    public void removeFromRootPane(Node node) {
        mainController.getGameView().getRootPane().getChildren().remove(node);
    }
}
