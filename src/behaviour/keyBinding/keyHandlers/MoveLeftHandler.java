package behaviour.keyBinding.keyHandlers;

import behaviour.keyBinding.KeyHandler;
import behaviour.keyBinding.KeyMap;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Moves {@link Node} in {@link KeyMap} to the left by 1 pixel.
 */
public class MoveLeftHandler extends KeyHandler {

    private ImageView imageView = null;

    /**
     * Constructor to the keyHandler.
     */
    public MoveLeftHandler(ImageView imageView) {
        super.pressed = false;
        this.imageView = imageView;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            if (imageView.getX() + imageView.getTranslateX() <= 0) {
                return;
            }
            imageView.setTranslateX(imageView.getTranslateX() - 1);
        });
    }
}