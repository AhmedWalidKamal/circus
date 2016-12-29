package controller;

import behaviour.keyBinding.KeyMap;
import behaviour.keyBinding.keyHandlers.AKeyHandler;
import behaviour.keyBinding.keyHandlers.DKeyHandler;
import behaviour.keyBinding.keyHandlers.LeftArrowKeyHandler;
import behaviour.keyBinding.keyHandlers.RightArrowKeyHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Player;
import model.characters.Character;
import model.characters.supportedCharacters.GreenClown;
import model.characters.supportedCharacters.RedClown;
import view.gui.app.Main;
import view.gui.gameplay.GameViewController;

import java.util.List;

/**
 * Acts as a controller to players behavior, has references to model that allow
 * it to create new players and updates them whenever it is required (e.g:
 * a signal from {@link InputController} to move a player, update some player's
 * score... etc) and update the view accordingly.
 */
public class PlayersController {
    private MainController mainController = null;
    private static final double SIDE_DISTANCE = 50.0;
    private static final double BOTTOM_DISTANCE = 15.0;

    public PlayersController(final MainController mainController) {
        this.mainController = mainController;
    }

    public void start() {
        Character redClown = new RedClown();
        Character greenClown = new GreenClown();
        AnchorPane.setBottomAnchor(redClown.getImageView(), BOTTOM_DISTANCE);
        AnchorPane.setBottomAnchor(greenClown.getImageView(), BOTTOM_DISTANCE);
        AnchorPane.setLeftAnchor(redClown.getImageView(), SIDE_DISTANCE);
        AnchorPane.setRightAnchor(greenClown.getImageView(), SIDE_DISTANCE);
        mainController.addToRootPane(redClown.getImageView());
        mainController.addToRootPane(greenClown.getImageView());

        //Testing purpose, KeyMap should be included inside Character/Player class.
        KeyMap redClownKeyMap = new KeyMap(redClown.getImageView());
        KeyMap greenClownKeyMap = new KeyMap(greenClown.getImageView());
        redClownKeyMap.addKeyHandler(new LeftArrowKeyHandler());
        redClownKeyMap.addKeyHandler(new RightArrowKeyHandler());
        greenClownKeyMap.addKeyHandler(new AKeyHandler());
        greenClownKeyMap.addKeyHandler(new DKeyHandler());
        mainController.getInputController().addKeyMap(redClownKeyMap);
        mainController.getInputController().addKeyMap(greenClownKeyMap);
    }
}
