package controller;

import javafx.scene.layout.AnchorPane;
import model.Player;
import model.characters.Character;
import model.characters.supportedCharacters.GreenClown;
import model.characters.supportedCharacters.RedClown;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a controller to players behavior, has references to model that allow
 * it to create new players and updates them whenever it is required (e.g:
 * a signal from {@link InputController} to move a player, update some player's
 * score... etc) and update the view accordingly.
 */
public final class PlayersController {
    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    /**
     * Distance to be set initially from the side of the screen for each
     * character, one on the right, one on the left.
     */
    private static final double SIDE_DISTANCE = 50.0;

    /**
     * Distance to be set initially from the bottom of the screen for both
     * characters.
     */
    private static final double BOTTOM_DISTANCE = 15.0;

    private List<Player> players = null;

    /**
     * Constructs a new {@link PlayersController}
     * @param mainController
     */
    public PlayersController(final MainController mainController) {
        this.mainController = mainController;
        players = new ArrayList<>();
    }

    public void prepareGame() {
        Player player1 = new Player();
        Player player2 = new Player();

        Character redClown = new RedClown();
        Character greenClown = new GreenClown();
        AnchorPane.setLeftAnchor(redClown.getImageView(), SIDE_DISTANCE);
        AnchorPane.setBottomAnchor(redClown.getImageView(), BOTTOM_DISTANCE);
        redClown.setX(SIDE_DISTANCE);
        redClown.setY(mainController.getGameView().getRootPane().prefHeightProperty()
                .doubleValue() - BOTTOM_DISTANCE - greenClown.getImageView()
                .getImage().getHeight());
        AnchorPane.setRightAnchor(greenClown.getImageView(), SIDE_DISTANCE);
        AnchorPane.setBottomAnchor(greenClown.getImageView(), BOTTOM_DISTANCE);
        greenClown.setX(mainController.getGameView().getRootPane().prefWidthProperty()
                .doubleValue() - SIDE_DISTANCE - greenClown.getImageView()
                .getImage().getWidth());
        greenClown.setY(mainController.getGameView().getRootPane().prefHeightProperty()
                .doubleValue() - BOTTOM_DISTANCE - greenClown.getImageView()
                .getImage().getHeight());

        mainController.getInputController().addKeyMap(redClown.getKeyMap());
        mainController.getInputController().addKeyMap(greenClown.getKeyMap());
        mainController.addToRootPane(redClown.getImageView());
        mainController.addToRootPane(greenClown.getImageView());

        player1.setCharacter(redClown);
        player2.setCharacter(greenClown);
        players.add(player1);
        players.add(player2);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
