package behaviour.keyBinding;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 * A handler to a key input that executes a pre-defined command on its {@link KeyMap}'s
 * {@link Node} whenever this key is triggered.
 */
public abstract class KeyHandler {
    /**
     * {@link KeyCode} key code to be acted upon when triggered.
     */
    protected KeyCode keyCode = null;

    /**
     * {@link KeyMap} containing this key handler.
     */
    protected KeyMap keyMap = null;

    /**
     * A boolean value defining whether the key to this key handler is pressed
     * or released.
     */
    protected boolean pressed;

    public abstract void execute();

    public KeyCode getKeyCode() {
        return this.keyCode;
    }

    public KeyMap getKeyMap() {
        return this.keyMap;
    }

    public void setKeyMap(KeyMap keyMap) {
        this.keyMap = keyMap;
    }

    public void setPressed(final boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isPressed() {
        return pressed;
    }
}
