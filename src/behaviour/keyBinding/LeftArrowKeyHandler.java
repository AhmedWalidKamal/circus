package behaviour.keyBinding;

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
        Node node = super.keyMap.getNode();
        node.setTranslateX(node.getTranslateX() - 5);
    }
}
