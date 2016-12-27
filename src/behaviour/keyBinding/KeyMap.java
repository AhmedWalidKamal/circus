package behaviour.keyBinding;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class KeyMap {
    private Map<KeyCode, KeyHandler> keyMap = null;
    private Node node = null;
    private Pane pane = null;

    public KeyMap(Node node, Pane pane) {
        keyMap = new HashMap<>();
        this.node = node;
        this.pane = pane;
        setOnKeyPressed(node);
        setOnKeyReleased(node);
    }

    public void addKeyHandler(KeyHandler keyHandler) {
        keyHandler.setKeyMap(this);
        keyMap.put(keyHandler.getKeyCode(), keyHandler);
    }

    public Node getNode() {
        return this.node;
    }

    private void setOnKeyPressed(Node node) {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
        pane.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
            if (handler.getState() == KeyState.RELEASED) {
                continue;
            }
            new Thread("Execute " + handler.toString()) {
                @Override
                public void run() {
                    handler.execute();
                }
            }.start();
        }
    }
}
