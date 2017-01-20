package controller;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Player;
import model.Shelf;
import util.Score;

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
     * The total time for the game.
     */
    private static final Integer GAMETIME = 60;

    /**
     * Timeline object that progresses the actual time.
     */
    private Timeline timeline = null;

    /**
     * The label that contains the current time.
     */
    private final Label timerLabel;

    /**
     * The total time for the game.
     */
    private final IntegerProperty timeSeconds;

    /**
     * Constructs a new {@link GameUtilController} that is used to control
     * game utilities.
     * @param mainController
     */
    public GameUtilController(final MainController mainController) {
        this.mainController = mainController;
        this.shelves = new ArrayList<>();
        this.timeline = new Timeline();
        this.timerLabel = new Label();
        this.timeSeconds = new SimpleIntegerProperty(GAMETIME);
        this.counter = -1;
    }

    /**
     * Prepares game before the beginning of starting any actual behavior (adds
     * shelves, score... etc.).
     */
    public void prepareGame() {
        final Shelf leftShelf = new Shelf(Y_SHELF, Shelf.Orientation.LEFT);
        shelves.add(leftShelf);
        AnchorPane.setLeftAnchor(leftShelf.getImageView(), SIDE_ANCHOR_DISTANCE);
        mainController.getGameView().getRootPane().getChildren().add(
                leftShelf.getImageView());

        final Shelf rightShelf = new Shelf(Y_SHELF, Shelf.Orientation.RIGHT);
        shelves.add(rightShelf);
        AnchorPane.setRightAnchor(rightShelf.getImageView(), SIDE_ANCHOR_DISTANCE);
        mainController.getGameView().getRootPane().getChildren().add(
                rightShelf.getImageView());
        initializeGameTimer();
        //initializePlayersScores();
    }

	/**
     * Initializes the game timer, creates the label that holds the time and
     * adds it to the game view.
     */
    private void initializeGameTimer() {
    	this.timerLabel.textProperty().bind(timeSeconds.asString());
        this.timerLabel.setTextFill(Color.RED);
        this.timerLabel.setStyle("-fx-font-size: 4em;");
        this.timeSeconds.set(GAMETIME);
        this.timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(GAMETIME + 1),
                new KeyValue(timeSeconds, 0)));
        this.timeline.play();
        final StackPane stackPane = new StackPane();
        stackPane.getChildren().add(this.timerLabel);
        StackPane.setAlignment(this.timerLabel, Pos.TOP_CENTER);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);
        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        this.mainController.getGameView().
        getRootPane().getChildren().add(stackPane);
	}

	public Shelf getNextShelf() {
        counter = (counter + 1) % shelves.size();
        return shelves.get(counter);
    }

	public void updateScore(final Player player) {
		final Score currentScore = player.getScore();
		currentScore.setPoints(currentScore.getPoints() + 1);
		player.setScore(currentScore);
	}
}
