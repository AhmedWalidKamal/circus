package behaviour.keyBinding;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class AKeyHandler extends KeyHandler {
    public AKeyHandler() {
        super.keyCode = KeyCode.A;
        super.pressed = false;
    }

    public AKeyHandler(KeyMap keyMap) {
        super.keyCode = KeyCode.A;
        super.pressed = false;
        super.keyMap = keyMap;
    }

    @Override
    public void execute() {
        Node node = super.keyMap.getNode();
        node.setTranslateX(node.getTranslateX() - 5);
    }
}
