package behaviour.keyBinding.keyHandlers;

import behaviour.keyBinding.KeyHandler;
import behaviour.keyBinding.KeyMap;
import javafx.application.Platform;
import javafx.scene.Node;
import logs.LogsManager;

public class MoveLeftHandler extends KeyHandler {

    public MoveLeftHandler(KeyMap keyMap) {
        super.pressed = false;
        super.keyMap = keyMap;
    }

    public MoveLeftHandler() {
        super.pressed = false;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            Node node = super.keyMap.getNode();
            node.setTranslateX(node.getTranslateX() - 1);
            LogsManager.getInstance().info("PLAYER MOVED TO THE LEFT");
        });
    }
}