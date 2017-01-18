package view.gui.mainmenu;

import javafx.scene.layout.Pane;

public class MainMenu {
	/**
     * {@link Pane} root pane which acts as a root for all elements inside a
     * FXML view, this pane usually requests focus in {@link MainMenuController}.
     */
    private Pane rootPane = null;

    /**
     * Instantiates a new {@link MainMenu}.
     */
    public MainMenu() {
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
