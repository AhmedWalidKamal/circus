package view.gui.endgame;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

public class EndGameViewHelper {

    private static EndGameViewHelper instance;
    private MenuItem returnToMenuMainButton;
    private MenuItem restartGameButton;
    private MenuItem exitGameButton;

    private EndGameViewHelper() {

    }

    public static EndGameViewHelper getInstance() {
        if (instance == null) {
            instance = new EndGameViewHelper();
        }
        return instance;
    }

    public void configureEndGameScene(final Pane root, final Text title, final Text winner) {
        this.restartGameButton = new MenuItem("RESTART GAME");
        this.returnToMenuMainButton = new MenuItem("RETURN TO MAIN MENU");
        this.exitGameButton = new MenuItem("EXIT GAME");
        MenuBox endGameMenu = new MenuBox(
                restartGameButton,
                returnToMenuMainButton,
                exitGameButton);
        endGameMenu.setLayoutX(170);
        endGameMenu.setLayoutY(350);
        root.getChildren().add(endGameMenu);
        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,50));
        winner.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,50));
    }

	public MenuItem getRestartGameButton() {
        return this.restartGameButton;
    }

    public MenuItem getReturnToMenuMainButton() {
        return this.returnToMenuMainButton;
    }

    public MenuItem getExitGameButton() {
        return this.exitGameButton;
    }
}
