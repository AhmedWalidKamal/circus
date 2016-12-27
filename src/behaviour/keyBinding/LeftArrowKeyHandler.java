package behaviour.keyBinding;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class LeftArrowKeyHandler extends KeyHandler {
    private KeyState state = null;
    private KeyCode keyCode = null;
    private KeyMap keyMap = null;

    public LeftArrowKeyHandler(KeyMap keyMap) {
        keyCode = KeyCode.LEFT;
        state = KeyState.RELEASED;
        this.keyMap = keyMap;
    }

    public LeftArrowKeyHandler() {
        keyCode = KeyCode.LEFT;
        state = KeyState.RELEASED;
    }

    @Override
    public void execute() {
        Node node = this.keyMap.getNode();
        node.setTranslateX(node.getTranslateX() - 5);
    }

    @Override
    public void setState(final KeyState state) {
        this.state = state;
    }

    @Override
    public KeyState getState() {
        return this.state;
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
}
