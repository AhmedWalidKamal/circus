package control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import model.save.ModelMemento;

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
        playerImageMap.put(playerOne, greenClownImage);
        attachFirstKeyMap(greenClownImage);
        mainController.getViewController().addToRootPane(greenClownImage);

        //Player Two.
        Character redClown = CharacterFactory.getInstance().createCharacter(
                RedClown.KEY);
        Player playerTwo = new Player("PLAYER TWO", redClown);
        ImageView redClownImage = new ImageView(redClown.getUrl());
        bindImageToCharacter(redClown, redClownImage);
        redClownImage.setX(SIDE_DISTANCE);
        playerImageMap.put(playerTwo, redClownImage);
        attachSecondKeyMap(redClownImage);
        mainController.getViewController().addToRootPane(redClownImage);
    }

    private void attachFirstKeyMap(final ImageView imageView) {
        KeyMap keyMap = new KeyMap(imageView);
        keyMap.addKeyHandler(KeyCode.LEFT, new MoveLeftHandler());
        keyMap.addKeyHandler(KeyCode.RIGHT, new MoveRightHandler());
        mainController.getInputController().addKeyMap(keyMap);
    }

    private void attachSecondKeyMap(final ImageView imageView) {
        KeyMap keyMap = new KeyMap(imageView);
        keyMap.addKeyHandler(KeyCode.A, new MoveLeftHandler());
        keyMap.addKeyHandler(KeyCode.D, new MoveRightHandler());
        mainController.getInputController().addKeyMap(keyMap);
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

    public List<Player> getPlayers() {
        List<Player> ret = new ArrayList<>();
        ret.addAll(playerImageMap.keySet());
        return ret;
    }

    public ImageView getCorrespondingImage(final Player player) {
        return playerImageMap.get(player);
    }

    public void collectMemento(final ModelMemento memento) {
        memento.setPlayers(getPlayers());
    }

    public void loadFromMemento(final ModelMemento memento) {
        loadDefaultCharacters();
        boolean f = false;
        for (Player player : memento.getPlayers()) {
            ImageView imageView = new ImageView(player.getCharacter().getUrl());
            copyCharacter(imageView, player.getCharacter());
            bindImageToCharacter(player.getCharacter(), imageView);
            playerImageMap.put(player, imageView);
            if (!f) {
                attachFirstKeyMap(imageView);
                f ^= true;
            } else {
                attachSecondKeyMap(imageView);
            }
            mainController.getViewController().addToRootPane(imageView);
        }

    }

    private void copyCharacter(final ImageView imageView, final Character
            character) {
        imageView.setX(character.getX());
        imageView.setY(character.getY());
        imageView.setTranslateX(character.getTranslateX());
        imageView.setTranslateY(character.getTranslateY());
    }
}