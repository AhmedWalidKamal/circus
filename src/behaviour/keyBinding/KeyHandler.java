package behaviour.keyBinding;

import javafx.scene.input.KeyCode;

import java.util.Observable;

public abstract class KeyHandler {
    public abstract void execute();

    public abstract void setState(KeyState state);

    public abstract KeyState getState();

    public abstract KeyCode getKeyCode();
}
