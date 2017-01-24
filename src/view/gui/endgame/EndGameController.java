package view.gui.endgame;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;

public class EndGameController implements Initializable, ControlledScenes {

	@FXML
	private AnchorPane root;

	private MainController mainController;

	private EndGameView endGameView;

	private ScenesNavigator myController;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		root.setFocusTraversable(true);
        endGameView = new EndGameView();
        endGameView.setRootPane(this.root);
	}

	@Override
	public void setScreenParent(final ScenesNavigator screenParent) {
		this.myController = screenParent;
		this.mainController = this.myController.getMainController();
		this.mainController.setEndGameView(endGameView);
        this.mainController.endGame();
	}
}
