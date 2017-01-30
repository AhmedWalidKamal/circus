package view.gui.levels_dialog;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

/**
 * The class resposible for displaying the difficulty menu
 * to the user.
 * @author Salma Ahmed
 */
public class LevelsDialogViewHelper {

    private static LevelsDialogViewHelper instance;
    private MenuItem easy;
    private MenuItem normal;
    private MenuItem hard;
    private LevelsDialogViewHelper() {

    }

    public static LevelsDialogViewHelper getInstance() {
        if (instance == null) {
            instance = new LevelsDialogViewHelper();
        }
        return instance;
    }

    /**
     * Configures the difficulty menu scene.
     * @param root, the root pane of the scene
     * @param title, the title text of the scene
     */
    public void configureTheLevelsDialog(final Pane root, final Text title) {
        easy = new MenuItem("EASY", 0.8);
        normal = new MenuItem("NORMAL", 0.8);
        hard = new MenuItem("HARD", 0.8);

        MenuBox levelsDialog = new MenuBox(
                easy,
                normal,
                hard);
        levelsDialog.setTranslateX(170);
        levelsDialog.setTranslateY(200);
        root.getChildren().add(levelsDialog);
        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

    }

    /**
     * Return the easy level button.
     * @return easy level button
     */
    public MenuItem getEasyLevelButton() {
        return easy;
    }
    /**
     * Return the normal level button.
     * @return normal level button
     */
    public MenuItem getNormalLevelButton() {
        return normal;
    }
    /**
     * Return the hard level button.
     * @return hard level button
     */
    public MenuItem getHardLevelButton() {
        return hard;
    }
}
