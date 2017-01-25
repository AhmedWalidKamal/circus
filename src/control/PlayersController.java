package control;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import behaviour.keyBinding.KeyMap;
import behaviour.keyBinding.keyHandlers.MoveLeftHandler;
import behaviour.keyBinding.keyHandlers.MoveRightHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import logs.LoggingManager;
import model.Player;
import model.characters.Character;
import model.characters.supportedCharacters.GreenClown;
import model.characters.supportedCharacters.RedClown;
import model.characters.util.CharacterFactory;

/**
 * Acts as a control to players behavior, has references to model that allow
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
    private static final double SIDE_DISTANCE = 25.0;

    /**
     * Distance to be set initially from the bottom of the screen for both
     * characters.
     */
    private static final double BOTTOM_DISTANCE = 15.0;

    private Map<Player, ImageView> playerImageMap = null;

    /**
     * Constructs a new {@link PlayersController}
     * @param mainController
     */
    public PlayersController(final MainController mainController) {
        this.mainController = mainController;
        playerImageMap = new LinkedHashMap<>();
    }

    public void prepareGame() {
        loadDefaultCharacters();

        //Player One.
        Character greenClown = CharacterFactory.getInstance().createCharacter(
                GreenClown.KEY);
        Player playerOne = new Player("PLAYER ONE", greenClown);
        ImageView greenClownImage = new ImageView(greenClown.getUrl());
        bindImageToCharacter(greenClown, greenClownImage);
        greenClownImage.setX(mainController.getViewController().getRootPanePrefWidth()
                - greenClown.getWidth() - SIDE_DISTANCE);
        KeyMap greenClownKeyMap = new KeyMap(greenClownImage);
        greenClownKeyMap.addKeyHandler(KeyCode.LEFT, new MoveLeftHandler());
        greenClownKeyMap.addKeyHandler(KeyCode.RIGHT, new MoveRightHandler());
        playerImageMap.put(playerOne, greenClownImage);
        mainController.getInputController().addKeyMap(greenClownKeyMap);
        mainController.getViewController().addToRootPane(greenClownImage);

        //Player Two.
        Character redClown = CharacterFactory.getInstance().createCharacter(
                RedClown.KEY);
        Player playerTwo = new Player("PLAYER TWO", redClown);
        ImageView redClownImage = new ImageView(redClown.getUrl());
        bindImageToCharacter(redClown, redClownImage);
        redClownImage.setX(SIDE_DISTANCE);
        KeyMap redClownKeyMap = new KeyMap(redClownImage);
        redClownKeyMap.addKeyHandler(KeyCode.A, new MoveLeftHandler());
        redClownKeyMap.addKeyHandler(KeyCode.D, new MoveRightHandler());
        playerImageMap.put(playerTwo, redClownImage);
        mainController.getInputController().addKeyMap(redClownKeyMap);
        mainController.getViewController().addToRootPane(redClownImage);
    }

    private void loadDefaultCharacters() {
        try {
            Class.forName("model.characters.supportedCharacters.GreenClown");
            Class.forName("model.characters.supportedCharacters.RedClown");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LoggingManager.getInstance().info("FAILED TO LOAD CLASS");
        }
    }

    private void bindImageToCharacter(final Character character, final ImageView image) {
        character.bindX(image.xProperty());
        character.bindY(image.yProperty());
        character.bindTranslateX(image.translateXProperty());
        character.bindTranslateY(image.translateYProperty());
        character.setHeight(image.getImage().getHeight());
        character.setWidth(image.getImage().getWidth());
        image.setY(mainController.getViewController().getRootPanePrefHeight()
                - BOTTOM_DISTANCE - image.getImage().getHeight());
        AnchorPane.setBottomAnchor(image, BOTTOM_DISTANCE);
    }

    public Collection<Player> getPlayers() {
        return playerImageMap.keySet();
    }

    public ImageView getCorrespondingImage(final Player player) {
        return playerImageMap.get(player);
    }
}