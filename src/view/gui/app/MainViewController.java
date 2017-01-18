package view.gui.app;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class MainViewController {
	@FXML
	private Pane currentPane;

	public void setCurrentPane(final Node node) {
		this.currentPane.getChildren().setAll(node);
	}
}
