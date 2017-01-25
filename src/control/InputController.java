package control;

import java.util.ArrayList;
import java.util.List;

import behaviour.keyBinding.KeyMap;
import javafx.scene.input.KeyCode;
import logs.LogsManager;
import util.PauseableThread;

/**
 * Acts as a link between view and registered keymaps, whenever view takes in
 * an input it signals it to {@link InputController}, having references to all
 * keymaps it can execute a command accordingly.
 */
public final class InputController extends PauseableThread {
    /**
     * List for all registered {@link KeyMap}s.
     */
    private List<KeyMap> keyMapList = null;

    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    private boolean paused = false;

    private static final String EASY_LEVEL = "EASY";
    private static final String NORMAL_LEVEL = "NORMAL";

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
    public void addKeyMap(final KeyMap keyMap) {
        keyMapList.add(keyMap);
    }

    /**
     * Executes all registered key commands that can handle this {@link KeyCode}.
     * @param keyCode Signaled {@link KeyCode} that it's needed to execute its
     * command.
     * @param pressed Boolean value to define if this key is pressed or released.
     */
    public void executeKeyCommand(final KeyCode keyCode, final boolean pressed) {
        if (paused) {
            return;
        }
        if (keyCode == KeyCode.ESCAPE) {
            mainController.getGameView().getPauseMenuPane().setVisible(true);
            mainController.getGameView().getPauseMenuPane().toFront();
            mainController.getGameView().getPauseMenuPane().requestFocus();

            mainController.pause();
            LogsManager.getInstance().info("GAME PAUSED");
        }
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

    @Override
    public void run() {
        while (true) {
            try {
            	if (mainController.
            			getLevelsController().
            			getDifficultyLevel().getLevelKey().
            			equals(EASY_LEVEL)) {
            		InputController.sleep(5);
            	} else if (mainController.
            			getLevelsController().
            			getDifficultyLevel().getLevelKey().
            			equals(NORMAL_LEVEL)) {
            		InputController.sleep(3);
            	} else {
            		InputController.sleep(1);
            	}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (paused)
                continue;
            for (KeyMap keyMap : keyMapList) {
                keyMap.executeAllPressedKeyCommands();
            }
        }
    }

    @Override
    public void pauseThread() {
        paused = true;
        for (KeyMap keyMap : keyMapList) {
            keyMap.setAllKeyHandlers(false);
        }
    }

    @Override
    public void resumeThread() {
        paused = false;
    }
}
