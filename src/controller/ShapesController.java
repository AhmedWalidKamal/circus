package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Acts as a controller to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class ShapesController {
    /**
     * {@link MainController} reference.
     */
    private MainController mainController = null;

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
    }
}
