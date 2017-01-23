package controller;

import behaviour.keyBinding.KeyMap;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a link between view and registered keymaps, whenever view takes in
 * an input it signals it to {@link InputController}, having references to all
 * keymaps it can execute a command accordingly.
 */
public final class InputController extends Thread {
    /**
     * List for all registered {@link KeyMap}s.
     */
    private List<KeyMap> keyMapList = null;

    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    private boolean paused = false;

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
        System.out.println(keyCode.getName() + " " + pressed);
        Thread executionThread = new Thread(() -> {
            for (KeyMap keyMap : keyMapList) {
                if (keyMap.containsKey(keyCode)) {
                    keyMap.setKeyHandlerPressed(keyCode, pressed);
                }
            }
        });
        executionThread.setDaemon(true);
        executionThread.start();
    }

    public void executeKeyType(final KeyCode keyCode) {
        if (keyCode == KeyCode.ESCAPE) {
            if (paused) {
                paused = false;
            } else {
                paused = true;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            for (KeyMap keyMap : keyMapList) {
                keyMap.executeAllPressedKeyCommands();
            }
            try {
                this.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
