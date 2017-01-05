package view.gui.gameplay;

import javafx.scene.layout.Pane;

/**
 * A passive view that is part of the MVC, provides data necessary to the game's
 * main view.
 */
public class GameView {
    /**
     * {@link Pane} root pane which acts as a root for all elements inside a
     * FXML view, this pane usually requests focus in {@link GameViewController}.
     */
    private Pane rootPane = null;

    /**
     * Instantiates a new {@link GameView}.
     */
    public GameView() {
        rootPane = new Pane();
    }

    /**
     * Sets root {@link Pane} which is the root for all elements inside a FXML
     * view.
     * @param rootPane root pane of all elements inside a FXML view.
     */
    protected void setRootPane(final Pane rootPane) {
        this.rootPane = rootPane;
    }

    /**
     * Gets the root pane of a game view.
     * @return root pane.
     */
    public Pane getRootPane() {
        return rootPane;
    }
}
