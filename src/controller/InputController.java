package controller;

import behaviour.keyBinding.KeyMap;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a link between view and registered keymapss, whenever view takes in
 * an input it signals it to {@link InputController}, having references to all
 * keymaps it can execute a command accordingly.
 */
public class InputController {

    /**
     * List for all registered {@link KeyMap}s.
     */
    private List<KeyMap> keyMapList = null;

    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    /**
     * Creates a new instance of {@link InputController}.
     */
    public InputController(final MainController mainController) {
        keyMapList = new ArrayList<>();
        this.mainController = mainController;
    }

    /**
     * Adds a new {@link KeyMap} to List of registered keyMaps.
     * @param keyMap
     */
    public void addKeyMap(KeyMap keyMap) {
        keyMapList.add(keyMap);
    }

    /**
     * Executes all registered key commands that can handle this {@link KeyCode}.
     * @param keyCode Signaled {@link KeyCode} that it's needed to execute its
     * command.
     * @param pressed Boolean value to define if this key is pressed or released.
     */
    public void executeKeyCommand(final KeyCode keyCode, final boolean pressed) {
        for (KeyMap keyMap : keyMapList) {
            if (keyMap.containsKey(keyCode)) {
                keyMap.setKeyHandlerPressed(keyCode, pressed);
            }
            keyMap.executeAllPressedKeyCommands();
        }
    }
}
