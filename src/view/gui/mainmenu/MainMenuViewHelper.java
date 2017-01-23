package view.gui.mainmenu;



import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.MenuBox;
import util.MenuItem;
import view.gui.app.Main;
import view.gui.app.util.SceneNavigator;

import java.io.IOException;

public class MainMenuViewHelper {

    private static MainMenuViewHelper instance;
    private MenuItem newGame;
    private MenuItem loadGame;
    private MenuItem exit;
    private MainMenuViewHelper() {

    }

    public static MainMenuViewHelper getInstance() {
        if (instance == null) {
            instance = new MainMenuViewHelper();
        }
        return  instance;
    }

    public void configureTheMainMenu(Pane root, Text title) {


        newGame = new MenuItem("NEW GAME");
        loadGame = new MenuItem("LOAD GAME");
        exit = new MenuItem("EXIT");

        MenuBox menu = new MenuBox(
                newGame,
                loadGame,
                new MenuItem("OPTIONS"),
                new MenuItem("HELP"),
                exit);
        menu.setTranslateX(100);
        menu.setTranslateY(450);
        root.getChildren().add(menu);

        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

    }

    public MenuItem getNewGameButton() {
        return newGame;
    }
    public MenuItem getLoadGameButton() {
        return loadGame;
    }
    public MenuItem getExitButton() {
        return exit;
    }


}
