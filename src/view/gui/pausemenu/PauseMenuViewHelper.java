package view.gui.pausemenu;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

public class PauseMenuViewHelper {

    private static PauseMenuViewHelper instance;
    private MenuItem resumeButton;
    private MenuItem optionsButton;
    private MenuItem saveButton;
    private MenuItem returnToMenuMainButton;

    private PauseMenuViewHelper() {

    }

    public static PauseMenuViewHelper getInstance() {
        if (instance == null) {
            instance = new PauseMenuViewHelper();
        }
        return instance;
    }

    public void configureThePauseMenu(final Pane root, final Text title) {
        resumeButton = new MenuItem("RESUME", 0.5);
        optionsButton = new MenuItem("OPTIONS", 0.5);
        saveButton = new MenuItem("SAVE", 0.5);
        returnToMenuMainButton = new MenuItem("RETURN TO MAIN MENU", 0.5);

        final MenuBox pauseMenu = new MenuBox(
                resumeButton,
                optionsButton,
                saveButton,
                returnToMenuMainButton);
        pauseMenu.setLayoutX(170);
        pauseMenu.setLayoutY(200);
        root.getChildren().add(pauseMenu);
        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,50));
    }

    public MenuItem getResumeButton() {
        return resumeButton;
    }

    public MenuItem getOptionsButton() {
        return optionsButton;
    }

    public MenuItem getSaveButton() {
        return saveButton;
    }

    public MenuItem getReturnToMenuMainButton() {
        return returnToMenuMainButton;
    }
}
