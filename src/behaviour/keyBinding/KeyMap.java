package behaviour.keyBinding;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Holds a keyMap to each player that allows the execution of some operations
 * on its representation in view.
 */
public class KeyMap {
    private Map<KeyCode, KeyHandler> keyMap = null;
    private Node node = null;

    public KeyMap(Node node) {
        keyMap = new HashMap<>();
        this.node = node;
    }

    public void addKeyHandler(KeyHandler keyHandler) {
        keyHandler.setKeyMap(this);
        keyMap.put(keyHandler.getKeyCode(), keyHandler);
    }

    public Node getNode() {
        return this.node;
    }

    public boolean containsKey(KeyCode keyCode) {
        return keyMap.containsKey(keyCode);
    }

    public void executeKeyCommand(KeyCode keyCode, boolean pressed) {
        if (!containsKey(keyCode)) {
            return;
        }
        KeyHandler keyHandler = keyMap.get(keyCode);
        keyHandler.setPressed(pressed);
        for (KeyHandler handler : keyMap.values()) {
            if (handler.isPressed()) {
                new Thread("Execute " + keyCode.getName()) {
                    @Override
                    public void run() {
                        handler.execute();
                    }
                }.start();
            }
        }
    }
}
