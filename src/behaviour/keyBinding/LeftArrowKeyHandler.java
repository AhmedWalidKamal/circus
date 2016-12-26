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

    @Override
    public void execute() {
        Node node = this.keyMap.getNode();
        //TODO: execute handler on node.
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
}
