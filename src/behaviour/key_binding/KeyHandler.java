package behaviour.key_binding;

import javafx.scene.Node;

/**
 * A handler to a key input that executes a pre-defined command on its {@link KeyMap}'s
 * {@link Node} whenever this key is triggered.
 */
public abstract class KeyHandler {
    /**
     * {@link KeyMap} containing this key handler.
     */
    protected KeyMap keyMap = null;

    /**
     * A boolean value defining whether the key to this key handler is pressed
     * or released.
     */
    protected boolean pressed;

    /**
     * Executes key command upon the node in the {@link KeyMap}.
     */
    public abstract void execute();

    /**
     * Gets {@link KeyMap} containing this {@link KeyHandler}.
     * @return
     */
    public KeyMap getKeyMap() {
        return this.keyMap;
    }

    /**
     * Sets KeyMap to this KeyHandler.
     * @param keyMap keyMap.
     */
    public void setKeyMap(KeyMap keyMap) {
        this.keyMap = keyMap;
    }

    /**
     * Sets a boolean value representing whether this key is pressed or not.
     * @param pressed boolean value.
     */
    public void setPressed(final boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Checks whether the corresponding key is pressed or not.
     * @return boolean value.
     */
    public boolean isPressed() {
        return pressed;
    }
}
