package view.gui.options;


import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

public class OptionsDialogViewHelper {

    private static OptionsDialogViewHelper instance;
    private MenuItem addCharacters;
    private MenuItem addShapes;
    /**
     * Close button to close the dialog and return to MainMenu
     */
    private MenuItem close;
    private OptionsDialogViewHelper() {

    }

    public static OptionsDialogViewHelper getInstance() {
        if (instance == null) {
            instance = new OptionsDialogViewHelper();
        }
        return  instance;
    }

    public void configureOptionsDialog(final Pane root, final Text title) {
        addCharacters = new MenuItem("ADD CHARACTER", 0.8);
        addShapes = new MenuItem("ADD SHAPE", 0.8);
        close = new MenuItem("CLOSE",0.8);

        MenuBox optionsDialog = new MenuBox(
                addCharacters,
                addShapes,
                close);
        optionsDialog.setTranslateX(170);
        optionsDialog.setTranslateY(200);
        root.getChildren().add(optionsDialog);
        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

    }

    public MenuItem getAddCharactersButton() {
        return addCharacters;
    }
    public MenuItem getAddShapesButton() {
        return addShapes;
    }
    public MenuItem getCloseButton() {return close;}
}
