package view.gui.mainmenu;



import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

public class MainMenuViewHelper {

    private static MainMenuViewHelper instance;
    private MenuItem newGame;
    private MenuItem loadGame;
    private MenuItem exit;
    private MainMenuViewHelper() {

    }

    public static MainMenuViewHelper getInstance() {
        if (instance == null) {
        	synchronized(MainMenuViewHelper.class) {
        		if (instance == null) {
        			instance = new MainMenuViewHelper();
        		}
        	}
        }
        return instance;
   }

   public void configureTheMainMenu(final Pane root, final Text title) {
        newGame = new MenuItem("NEW GAME", 0.65);
        loadGame = new MenuItem("LOAD GAME", 0.65);
        exit = new MenuItem("EXIT", 0.65);

        final MenuBox menu = new MenuBox(
                newGame,
                loadGame,
                new MenuItem("OPTIONS", 0.65),
                new MenuItem("HELP", 0.65),
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
