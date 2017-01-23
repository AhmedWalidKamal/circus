package behaviour.keyBinding;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds a keyMap to a {@link Node} that allows the execution of some operations
 * on it whenever a contained key is triggered.
 */
public class KeyMap {
    /**
     * Key mapping between {@link KeyCode} and its equivalent {@link KeyHandler}.
     */
    private Map<KeyCode, KeyHandler> keyMap = null;

    /**
     * {@link javafx.scene.Node} to act upon.
     */
    private Node node = null;

    /**
     * Instantiates a new KeyMap given the node to act upon when its keyHandler
     * is triggered whenever a key is pressed/released.
     * @param node
     */
    public KeyMap(final Node node) {
        keyMap = new HashMap<>();
        this.node = node;
    }

    /**
     * Adds a new keyHandler to this node.
     * @param keyHandler {@link KeyHandler} handler to key triggering.
     */
    public void addKeyHandler(KeyHandler keyHandler) {
        keyHandler.setKeyMap(this);
        keyMap.put(keyHandler.getKeyCode(), keyHandler);
    }

    /**
     * Gets the node acted upon.
     * @return {@link Node} node acted upon.
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * Checks if this key map has an equivalent {@link KeyHandler} to a given
     * {@link KeyCode}
     * @param keyCode {@link KeyCode}.
     * @return A boolean value defining if this keyMap contains this key code.
     */
    public synchronized boolean containsKey(KeyCode keyCode) {
        return keyMap.containsKey(keyCode);
    }

    /**
     * Executes all equivalent {@link KeyHandler}s that can react to the given
     * {@link KeyCode}.
     * @param keyCode {@link KeyCode} Given key code to be acted upon triggering.
     * @param pressed A boolean value defining whether the given key is pressed
     * or released.
     */
    public void executeKeyCommand(KeyCode keyCode, boolean pressed) {
        setKeyHandlerPressed(keyCode, pressed);
        executeAllPressedKeyCommands();
    }

    /**
     * Executes all contained {@link KeyHandler}s that are pressed.
     */
    public synchronized void executeAllPressedKeyCommands() {
        for (KeyHandler handler : keyMap.values()) {
            if (handler.isPressed()) {
                handler.execute();
            }
        }
    }

    /**
     * Sets an equivalent {@link KeyHandler} to triggered {@link KeyCode}
     * as pressed.
     * @param keyCode Triggered {@link KeyCode}.
     * @param pressed A boolean value defining whether this key is pressed or
     * released.
     */
    public synchronized void setKeyHandlerPressed(KeyCode keyCode, boolean pressed) {
        if (!containsKey(keyCode)) {
            throw new RuntimeException("No equivalent key handler");
        }
        KeyHandler keyHandler = keyMap.get(keyCode);
        keyHandler.setPressed(pressed);
    }
}
