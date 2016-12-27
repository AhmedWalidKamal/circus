package behaviour.keyBinding;

import javafx.scene.input.KeyCode;

public abstract class KeyHandler {

    public abstract void execute();

    public abstract void setState(KeyState state);

    public abstract KeyState getState();

    public abstract KeyCode getKeyCode();

    public abstract KeyMap getKeyMap();

    public abstract void setKeyMap(KeyMap keyMap);
}
