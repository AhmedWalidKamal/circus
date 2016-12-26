package behaviour.keyBinding;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;

public class KeyMap {
    private Map<KeyCode, KeyHandler> keyMap = null;
    private Node node;

    public KeyMap(Node node) {
        keyMap = new HashMap<>();
        this.node = node;
        setOnKeyPressed(node);
        setOnKeyReleased(node);
    }

    public void addKeyHandler(KeyHandler keyHandler) {
        keyMap.put(keyHandler.getKeyCode(), keyHandler);
    }

    public Node getNode() {
        return this.node;
    }

    private void setOnKeyPressed(Node node) {
        node.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                KeyHandler handler = keyMap.get(event.getCode());
                if (handler == null) {
                    return;
                }
                handler.setState(KeyState.PRESSED);
                executeKeyHandlers();
            }
        });
    }

    private void setOnKeyReleased(Node node) {
        node.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                KeyHandler handler = keyMap.get(event.getCode());
                if (handler == null) {
                    return;
                }
                handler.setState(KeyState.RELEASED);
                executeKeyHandlers();
            }
        });
    }

    private void executeKeyHandlers() {
        for (KeyHandler handler : keyMap.values()) {
            handler.execute();
        }
    }
}
