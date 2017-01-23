package behaviour.keyBinding.keyHandlers;

import behaviour.keyBinding.KeyHandler;
import behaviour.keyBinding.KeyMap;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class AKeyHandler extends KeyHandler {

    public AKeyHandler(KeyMap keyMap) {
        super.keyCode = KeyCode.A;
        super.pressed = false;
        super.keyMap = keyMap;
    }

    public AKeyHandler() {
        super.keyCode = KeyCode.A;
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