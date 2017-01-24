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

    public void configureTheLevelsDialog(Pane root, Text title) {
        easy = new MenuItem("EASY");
        medium = new MenuItem("MEDIUM");
        hard = new MenuItem("HARD");

        MenuBox menu = new MenuBox(
                easy,
                medium,
                hard);
        menu.setTranslateX(100);
        menu.setTranslateY(450);
        root.getChildren().add(menu);

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
