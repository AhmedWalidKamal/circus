package view.gui.endgame;

import javafx.scene.layout.Pane;

public class EndGameView {

    private Pane rootPane = null;

    public EndGameView() {
        rootPane = new Pane();
    }
    protected void setRootPane(final Pane rootPane) {
        this.rootPane = rootPane;
    }
    public Pane getRootPane() {
        return rootPane;
    }
}
