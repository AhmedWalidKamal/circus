package del;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Main controller class for the entire layout.
 */
public class MainController {

    /** Holder of a switchable vista. */
    @FXML
    private Pane vistaHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(final Node node) {
        vistaHolder.getChildren().setAll(node);
    }

}