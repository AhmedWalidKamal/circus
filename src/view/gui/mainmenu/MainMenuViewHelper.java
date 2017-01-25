package view.gui.mainmenu;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.MenuBox;
import util.MenuItem;

/**
 * Helper class for configuring the main menu buttons.
 * @author Mohamed Tolba
 */
public class MainMenuViewHelper {

    private static MainMenuViewHelper instance;
    private MenuItem newGame;
    private MenuItem loadGame;
    private MenuItem exit;
    private MenuItem options;
    private MenuItem help;
    private Pane root = null;
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

    /**
     * Configures the main menu scene.
     * @param root, the root pane for the scene
     * @param title, the title of the scene
     */
   public void configureTheMainMenu(final Pane root, final Text title) {
        newGame = new MenuItem("NEW GAME", 0.65);
        loadGame = new MenuItem("LOAD GAME", 0.65);
        exit = new MenuItem("EXIT", 0.65);
        options = new MenuItem("OPTIONS",0.65);
        help = new MenuItem("HELP",0.65);

        final MenuBox mainMenu = new MenuBox(
                newGame,
                loadGame,
                options,
                help,
                exit);
        mainMenu.setTranslateX(100);
        mainMenu.setTranslateY(450);
        mainMenu.setId("mainMenu");
        this.root = root;
        root.getChildren().add(mainMenu);
        title.setFont(Font.font("Tw Cen MT Condensed",
        		FontWeight.SEMI_BOLD, 50));
    }

   /**
    * Returns the new game button.
    * @return new game button
    */
    public MenuItem getNewGameButton() {
        return newGame;
    }
    /**
     * Returns the load game button.
     * @return load button
     */
    public MenuItem getLoadGameButton() {
        return loadGame;
    }
    /**
     * Returns the options button.
     * @return options button
     */
    public MenuItem getOptionsButton() {
    	return options;
    }
    /**
     * Returns the help button.
     * @return help button
     */
    public MenuItem getHelpButton() {
    	return help;
    }
    /**
     * Returns the exit game button.
     * @return exit button
     */
    public MenuItem getExitButton() {
        return exit;
    }
    /**
     * Returns the main menu root pane.
     * @return the root pane
     */
    public Node getMainMenuPane() {
    	return root;
    }
}
