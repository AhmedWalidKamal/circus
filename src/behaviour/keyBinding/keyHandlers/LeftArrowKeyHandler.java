package behaviour.keyBinding.keyHandlers;

import behaviour.keyBinding.KeyHandler;
import behaviour.keyBinding.KeyMap;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class LeftArrowKeyHandler extends KeyHandler {

    public LeftArrowKeyHandler(KeyMap keyMap) {
        super.keyCode = KeyCode.LEFT;
        super.pressed = false;
        super.keyMap = keyMap;
    }

    public LeftArrowKeyHandler() {
        super.keyCode = KeyCode.LEFT;
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
