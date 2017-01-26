package behaviour.keyBinding.keyHandlers;

import behaviour.keyBinding.KeyHandler;
import behaviour.keyBinding.KeyMap;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * * Moves {@link Node} in {@link KeyMap} to the right by 1 pixel.
 */
public class MoveRightHandler extends KeyHandler {
    private ImageView imageView = null;
    private double bound;

    /**
     * Constructor to the keyHandler.
     */
    public MoveRightHandler(final ImageView imageView, final double bound) {
        super.pressed = false;
        this.imageView = imageView;
        this.bound = bound;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            Node node = super.keyMap.getNode();
            if (imageView.getX() + imageView.getTranslateX()
                    + imageView.getImage().getWidth() >= bound) {
                return;
            }
            node.setTranslateX(node.getTranslateX() + 1);
        });
    }
}
