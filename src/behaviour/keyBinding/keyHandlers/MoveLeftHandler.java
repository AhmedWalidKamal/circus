package behaviour.keyBinding.keyHandlers;

import behaviour.keyBinding.KeyHandler;
import behaviour.keyBinding.KeyMap;
import javafx.application.Platform;
import javafx.scene.Node;

/**
 * Moves {@link Node} in {@link KeyMap} to the left by 1 pixel.
 */
public class MoveLeftHandler extends KeyHandler {

    /**
     * Constructor to the keyHandler.
     * @param keyMap {@link KeyMap} containing this handler.
     */
    public MoveLeftHandler(KeyMap keyMap) {
        super.pressed = false;
        super.keyMap = keyMap;
    }

    /**
     * Constructor to the keyHandler.
     */
    public MoveLeftHandler() {
        super.pressed = false;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            Node node = super.keyMap.getNode();
            node.setTranslateX(node.getTranslateX() - 1);
        });
    }
}