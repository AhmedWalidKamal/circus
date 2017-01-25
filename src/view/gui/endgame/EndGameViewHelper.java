package view.gui.endgame;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

/**
 * The class resposible for setting up the end game
 * scene by configuring its buttons and messages.
 * @author Ahmed Walid
 */
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

    /**
     * Configures the end game scene.
     * @param root, the root pane of the scene
     * @param title, the title of the scene
     * @param winner, the text to be displayed to the winner
     */
    public void configureEndGameScene(final Pane root,
    		final Text title, final Text winner) {
        this.restartGameButton = new MenuItem("RESTART GAME", 0.9);
        this.returnToMenuMainButton
        = new MenuItem("RETURN TO MAIN MENU", 0.9);
        this.exitGameButton = new MenuItem("EXIT GAME", 0.9);
        MenuBox endGameMenu = new MenuBox(
                restartGameButton,
                returnToMenuMainButton,
                exitGameButton);
        endGameMenu.setLayoutX(170);
        endGameMenu.setLayoutY(350);
        root.getChildren().add(endGameMenu);
        title.setFont(Font.font("Tw Cen MT Condensed",
        		FontWeight.SEMI_BOLD,50));
        winner.setFont(Font.font("Tw Cen MT Condensed",
        		FontWeight.SEMI_BOLD,50));
    }

    /**
     * Returns a reference to the restart button.
     * @return restart button
     */
	public MenuItem getRestartGameButton() {
        return this.restartGameButton;
    }

	/**
     * Returns a reference to the return to
     * main menu button.
     * @return return to main menu button
     */
    public MenuItem getReturnToMenuMainButton() {
        return this.returnToMenuMainButton;
    }

    /**
     * Returns a reference to the exit game button.
     * @return exit game button
     */
    public MenuItem getExitGameButton() {
        return this.exitGameButton;
    }
}
