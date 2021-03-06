package control;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logs.LoggingManager;
import model.Player;
import model.Timer;
import model.save.MementoOriginator;
import model.save.ModelMemento;
import util.Score;
import util.Slider;

/**
 * Acts as a control to all game utilities (score, sliders, ... etc).
 */
public final class GameUtilController implements MementoOriginator {
    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    /**
     * List of {@link Slider} to be put in view.
     */
    private ArrayList<Slider> sliders = null;

    /**
     * Distance to be set from side anchors for sliders.
     */
    private static final double SIDE_ANCHOR_DISTANCE = -50;

    /**
     * Value of Y-Axis for the slider to be put in view.
     */
    private static final double Y_SLIDER = 100;

    private int counter;

    /**
     * The total time for the game.
     */
    private Integer gameTime = 60;

    /**
     * Timeline object that progresses the actual time.
     */
    private Timeline timeline = null;

    /**
     * The label that contains the current time.
     */
    private final Label timerLabel;

    private final Label firstPlayerLabel;
    private final Label secondPlayerLabel;

    /**
     * The total time for the game.
     */
    private Timer timer = null;

    private final IntegerProperty firstPlayerScore;
    private final IntegerProperty secondPlayerScore;

    /**
     * Constructs a new {@link GameUtilController} that is used to control
     * game utilities.
     * @param mainController
     */
    public GameUtilController(final MainController mainController) {
        this.mainController = mainController;
        this.sliders = new ArrayList<>();
        this.timeline = new Timeline();
        this.timerLabel = new Label();
        this.firstPlayerLabel = new Label();
        this.secondPlayerLabel = new Label();
        this.firstPlayerScore = new SimpleIntegerProperty(0);
        this.secondPlayerScore = new SimpleIntegerProperty(0);
        this.timer = new Timer(gameTime);
        this.counter = -1;
    }

    /**
     * Prepares game before the beginning of starting any actual behavior (adds
     * sliders, score... etc.).
     */
    public void prepareGame() {
        initializeSliders();
        initializeGameTimer();
        initializePlayersScores();
    }

    /**
     * Initializes the sliders in the game and adds the to the
     * root pane.
     */
    private void initializeSliders() {
        final Slider leftSlider = new Slider(Y_SLIDER, Slider.Orientation.LEFT);
        sliders.add(leftSlider);
        AnchorPane.setLeftAnchor(leftSlider.getImageView(), SIDE_ANCHOR_DISTANCE);
        mainController.getGameView().getRootPane().getChildren().add(
                leftSlider.getImageView());

        final Slider rightSlider = new Slider(Y_SLIDER, Slider.Orientation.RIGHT);
        sliders.add(rightSlider);
        AnchorPane.setRightAnchor(rightSlider.getImageView(), SIDE_ANCHOR_DISTANCE);
        mainController.getGameView().getRootPane().getChildren().add(
                rightSlider.getImageView());
    }

    /**
     * Initializes the players' scores displayed
     * on the top corners of the screen.
     */
	private void initializePlayersScores() {
		this.firstPlayerLabel.textProperty().bind(firstPlayerScore.asString());
		this.secondPlayerLabel.textProperty().bind(secondPlayerScore.asString());
		this.firstPlayerLabel.setTextFill(Color.RED);
		this.firstPlayerLabel.setStyle("-fx-font-size: 4em;");
		this.secondPlayerLabel.setTextFill(Color.RED);
		this.secondPlayerLabel.setStyle("-fx-font-size: 4em;");
        this.firstPlayerScore.set(mainController.getPlayersController()
                .getPlayers().get(1).getScore().getCurrentScore());
        this.secondPlayerScore.set(mainController.getPlayersController()
                .getPlayers().get(0).getScore().getCurrentScore());
        final StackPane pane1 = new StackPane();
        pane1.getChildren().add(this.firstPlayerLabel);
        StackPane.setAlignment(this.firstPlayerLabel, Pos.TOP_LEFT);
        AnchorPane.setRightAnchor(pane1, 0.0);
        AnchorPane.setLeftAnchor(pane1, 0.0);
        AnchorPane.setTopAnchor(pane1, 0.0);
        AnchorPane.setBottomAnchor(pane1, 0.0);
        this.mainController.getGameView().
        getRootPane().getChildren().add(pane1);
        final StackPane pane2 = new StackPane();
        pane2.getChildren().add(this.secondPlayerLabel);
        StackPane.setAlignment(this.secondPlayerLabel, Pos.TOP_RIGHT);
        AnchorPane.setRightAnchor(pane2, 0.0);
        AnchorPane.setLeftAnchor(pane2, 0.0);
        AnchorPane.setTopAnchor(pane2, 0.0);
        AnchorPane.setBottomAnchor(pane2, 0.0);
        this.mainController.getGameView().
        getRootPane().getChildren().add(pane2);
	}

	/**
     * Initializes the game timer, creates the label that holds the time and
     * adds it to the game view.
     */
    private void initializeGameTimer() {
    	this.timerLabel.textProperty().bind(timer.getCurrentTimeProperty().asString());
        this.timerLabel.setTextFill(Color.RED);
        this.timerLabel.setStyle("-fx-font-size: 4em;");
        this.timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(gameTime + 1),
                new KeyValue(timer.getCurrentTimeProperty(), 0)));
        this.timeline.play();
        this.timeline.setOnFinished(e -> this.mainController.getGameViewController().showEndGameScene());
        final StackPane stackPane = new StackPane();
        stackPane.getChildren().add(this.timerLabel);
        StackPane.setAlignment(this.timerLabel, Pos.TOP_CENTER);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);
        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        this.mainController.getViewController().addToRootPane(stackPane);
	}

	public Slider getNextSlider() {
        counter = (counter + 1) % sliders.size();
        return sliders.get(counter);
    }

	/**
	 * Updates the player's score.
	 * @param player, the player to add the score to.
	 */
	public void updateScore(final Player player) {
		final Score currentScore = player.getScore();
		currentScore.setCurrentScore(currentScore.getCurrentScore() + 100);
		player.setScore(currentScore);
        LoggingManager.getInstance().info(player.getName() + "'S SCORE IS NOW "+ currentScore.getCurrentScore());
		Platform.runLater(() -> {
			if (player.getCharacter().getKey().equals("greenClown")) {
				secondPlayerScore.set(player.getScore().getCurrentScore());
			} else {
				firstPlayerScore.set(player.getScore().getCurrentScore());
			}
		});
	}

	/**
	 * Pauses the timer.
	 */
	public void pauseTime() {
        timeline.pause();
    }

	/**
	 * Resumes the timer.
	 */
    public void resumeTime() {
        timeline.play();
    }

    @Override
    public void collectMemento(final ModelMemento memento) {
        memento.setTimer(timer);
    }

    @Override
    public void loadFromMemento(final ModelMemento memento) {
        timer = memento.getTimer();
        gameTime = timer.getCurrentTime();
    }
}
