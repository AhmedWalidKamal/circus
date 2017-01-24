package view.gui.mainmenu;



import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

public class MainMenuViewHelper {

    private static MainMenuViewHelper instance;
    private MainMenuController mainMenuController = null;

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

    public void setMainMenuController(final MainMenuController mainMenuController) {
    	this.mainMenuController = mainMenuController;
    }
    public void configureTheMainMenu(final Pane root, final Text title) {


        final MenuItem newGame = new MenuItem("NEW GAME");
        newGame.setOnMouseClicked(event -> {
            this.mainMenuController.loadGameScene();
        });

        final MenuItem exit = new MenuItem("EXIT");
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        final MenuBox menu = new MenuBox(
                newGame,
                new MenuItem("LOAD GAME"),
                new MenuItem("OPTIONS"),
                new MenuItem("HELP"),
                exit);
        menu.setTranslateX(100);
        menu.setTranslateY(450);
        root.getChildren().add(menu);

        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

    }
}
