package view.gui.levelsdialog;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

public class LevelsDialogViewHelper {

    private static LevelsDialogViewHelper instance;
    private MenuItem easy;
    private MenuItem medium;
    private MenuItem hard;
    private LevelsDialogViewHelper() {

    }

    public static LevelsDialogViewHelper getInstance() {
        if (instance == null) {
            instance = new LevelsDialogViewHelper();
        }
        return  instance;
    }

    public void configureTheLevelsDialog(final Pane root, final Text title) {
        easy = new MenuItem("EASY", 0.8);
        medium = new MenuItem("MEDIUM", 0.8);
        hard = new MenuItem("HARD", 0.8);

        MenuBox levelsDialog = new MenuBox(
                easy,
                medium,
                hard);
        levelsDialog.setTranslateX(170);
        levelsDialog.setTranslateY(200);
        root.getChildren().add(levelsDialog);
        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

    }

    public MenuItem getEasyLevelButton() {
        return easy;
    }
    public MenuItem getMediumLevelButton() {
        return medium;
    }
    public MenuItem getHardLevelButton() {
        return hard;
    }
}
