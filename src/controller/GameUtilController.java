package controller;

import javafx.scene.layout.AnchorPane;
import model.Shelf;

import java.util.ArrayList;

/**
 * Acts as a controller to all game utilities (score, shelves, ... etc).
 */
public final class GameUtilController {
    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    /**
     * List of {@link Shelf} to be put in view.
     */
    private ArrayList<Shelf> shelves = null;

    /**
     * Distance to be set from side anchors for shelves.
     */
    private static final double SIDE_ANCHOR_DISTANCE = -50;

    /**
     * Value of Y-Axis for the shelf to be put in view.
     */
    private static final double Y_SHELF = 100;

    private int counter;

    /**
     * Constructs a new {@link GameUtilController} that is used to control
     * game utilities.
     * @param mainController
     */
    public GameUtilController(final MainController mainController) {
        this.mainController = mainController;
        shelves = new ArrayList<>();
        counter = -1;
    }

    /**
     * Prepares game before the beginning of starting any actual behavior (adds
     * shelves, score... etc.).
     */
    public void prepareGame() {
        Shelf leftShelf = new Shelf(Y_SHELF, Shelf.Orientation.LEFT);
        shelves.add(leftShelf);
        AnchorPane.setLeftAnchor(leftShelf.getImageView(), SIDE_ANCHOR_DISTANCE);
        mainController.getGameView().getRootPane().getChildren().add(
                leftShelf.getImageView());

        Shelf rightShelf = new Shelf(Y_SHELF, Shelf.Orientation.RIGHT);
        shelves.add(rightShelf);
        AnchorPane.setRightAnchor(rightShelf.getImageView(), SIDE_ANCHOR_DISTANCE);
        mainController.getGameView().getRootPane().getChildren().add(
                rightShelf.getImageView());
    }

    public Shelf getNextShelf() {
        counter = (counter + 1) % shelves.size();
        return shelves.get(counter);
    }
}
