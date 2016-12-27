package behaviour.keyBinding;

import javafx.scene.input.KeyCode;

public abstract class KeyHandler {

    public abstract void execute();

    public abstract KeyCode getKeyCode();

    public abstract KeyMap getKeyMap();

    public abstract void setKeyMap(KeyMap keyMap);

    public abstract void setPressed(boolean pressed);

    public abstract boolean isPressed();
}
