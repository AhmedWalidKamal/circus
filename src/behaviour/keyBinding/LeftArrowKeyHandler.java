package behaviour.keyBinding;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class LeftArrowKeyHandler extends KeyHandler {
    private KeyCode keyCode = null;
    private KeyMap keyMap = null;
    private boolean pressed;

    public LeftArrowKeyHandler(KeyMap keyMap) {
        keyCode = KeyCode.LEFT;
        pressed = false;
        this.keyMap = keyMap;
    }

    public LeftArrowKeyHandler() {
        keyCode = KeyCode.LEFT;
        pressed = false;
    }

    @Override
    public void execute() {
        Node node = this.keyMap.getNode();
        node.setTranslateX(node.getTranslateX() - 5);
    }

    @Override
    public KeyCode getKeyCode() {
        return this.keyCode;
    }

    @Override
    public KeyMap getKeyMap() {
        return this.keyMap;
    }

    @Override
    public void setKeyMap(KeyMap keyMap) {
        this.keyMap = keyMap;
    }

    @Override
    public void setPressed(final boolean pressed) {
        this.pressed = pressed;
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }
}
