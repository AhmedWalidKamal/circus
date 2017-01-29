package view.gui.mainmenu;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import logs.LoggingManager;
import plugins.PluginLoader;
import view.gui.app.Main;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;
import view.gui.levelsdialog.LevelsDialogViewHelper;
import view.gui.mainmenu.util.GameData;
import view.gui.options.OptionsDialogViewHelper;

/**
 * The Controller responsible for the main menu.
 * @author Mohamed Tolba
 */
public class MainMenuController
implements Initializable, ControlledScenes {

    @FXML
    private Pane root;

    @FXML
    private Text title;

    @FXML
    private Pane levelsPane;

    @FXML
    private Text LevelsDialogTitle;

    @FXML
    private Pane optionsPane;

    @FXML
    private Text optionsDialogTitle;


    private ScenesNavigator sceneNavigator;
    private FileChooser fileChooser;

    private static final String EASY_LEVEL = "EASY";
    private static final String NORMAL_LEVEL = "NORMAL";
    private static final String HARD_LEVEL = "HARD";
    private static final String CHARACTER = "CHARACTER";
    private static final String SHAPE = "SHAPE";


    private GameData gameData;

    /**
     * Initializes the main menu scene.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
            root.setFocusTraversable(true);
            MainMenuViewHelper.getInstance().
            configureTheMainMenu(root, title);
            fileChooser = new FileChooser();
            LevelsDialogViewHelper.
            getInstance().configureTheLevelsDialog(this.levelsPane,
            		this.LevelsDialogTitle);
            OptionsDialogViewHelper.getInstance().
                    configureOptionsDialog(this.optionsPane,
                    		this.optionsDialogTitle);
            setVisibilityBindingLevelsDialogAndOptionsDialog();
            gameData = new GameData();
            configureNewGameButton();
            configureOptionsButton();
            configureExitButton();
            configureCloseButton();
            configureEasyLevelButton();
            configureNormalLevelButton();
            configureHardLevelButton();
            configureLoadGameButton();
            configureAddCharacterAndShapeButtons();
    }

    @Override
    public void setScreenParent(final ScenesNavigator screenParent) {
    	this.sceneNavigator = screenParent;
    }

    /**
     * Sets the visibility of the levels and options pane.
     */
    private void setVisibilityBindingLevelsDialogAndOptionsDialog() {
        levelsPane.managedProperty().bind(levelsPane.visibleProperty());
        levelsPane.setVisible(false);
        optionsPane.managedProperty().bind(optionsPane.visibleProperty());
        optionsPane.setVisible(false);

    }

    /**
     * Configures new game button's action.
     */
    private void configureNewGameButton() {
        MainMenuViewHelper.getInstance().getNewGameButton().setOnMouseClicked(event -> {
            LoggingManager.getInstance().info("NEW GAME STARTED");
            this.root.lookup("#mainMenu").setDisable(true);
            this.levelsPane.setVisible(true);
            this.levelsPane.toFront();
            this.levelsPane.requestFocus();
        });
    }

    /**
     * Configures load game button's action.
     */
    private void configureLoadGameButton() {
        MainMenuViewHelper.getInstance().getLoadGameButton().setOnMouseClicked(event -> {
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
            	if (file.toString().endsWith(".protobuff")
                        || file.toString().endsWith(".JSON")) {
                    sceneNavigator.loadGame(Main.GAMEVIEW_ID, Main.GAMEVIEW_URL,
                            Main.GAMEVIEW_STYLESHEET, file.getPath());
                    sceneNavigator.setScene(Main.GAMEVIEW_ID);
                } else {
                    LoggingManager.getInstance().error("Unsupported format.");
                }
            }
        });
    }

    /**
     * Configures exit game button's action.
     */

    private void configureExitButton() {
        MainMenuViewHelper.getInstance().
        getExitButton().setOnMouseClicked(event -> System.exit(0));
    }

    /**
     * Configures option button's action.
     */
    private void configureOptionsButton() {
         MainMenuViewHelper.getInstance().
         getOptionsButton().setOnMouseClicked(event -> {
             this.root.lookup("#mainMenu").setDisable(true);
             this.optionsPane.setVisible(true);
             this.optionsPane.toFront();
             this.optionsPane.requestFocus();
         });
    }

    private void configureAddCharacterAndShapeButtons() {
        OptionsDialogViewHelper.getInstance().getAddCharactersButton().setOnMouseClicked(event -> {
            File filePath = fileChooser.showOpenDialog(null);
            PluginLoader.getInstance().load(CHARACTER,filePath);
        });
        OptionsDialogViewHelper.getInstance().getAddShapesButton().setOnMouseClicked(event -> {
            File filePath = fileChooser.showOpenDialog(null);
            PluginLoader.getInstance().load(SHAPE,filePath);
        });
    }

    /**
     * Configures close button's action.
     */
    private void configureCloseButton() {
        OptionsDialogViewHelper.getInstance().
        getCloseButton().setOnMouseClicked(event -> {
            this.root.lookup("#mainMenu").setDisable(false);
            this.optionsPane.setVisible(false);
            this.optionsPane.toBack();
            this.root.requestFocus();
        });
    }

    /**
     * Configures easy level button's action.
     */
    private void configureEasyLevelButton() {
        LevelsDialogViewHelper.getInstance().getEasyLevelButton().setOnMouseClicked(event -> {
            LoggingManager.getInstance().info("EASY LEVEL SELECTED");
            this.gameData.setGameDifficulty(EASY_LEVEL);
            this.sceneNavigator.startGame(Main.GAMEVIEW_ID,
            		Main.GAMEVIEW_URL,
            		Main.GAMEVIEW_STYLESHEET, this.gameData);
            this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }

    /**
     * Configures normal level button's action.
     */
    private void configureNormalLevelButton() {
        LevelsDialogViewHelper.getInstance().
        getNormalLevelButton().setOnMouseClicked(event -> {
        	this.gameData.setGameDifficulty(NORMAL_LEVEL);
            LoggingManager.getInstance().info("NORMAL LEVEL SELECTED");
        	this.sceneNavigator.startGame(Main.GAMEVIEW_ID,
            		Main.GAMEVIEW_URL,
            		Main.GAMEVIEW_STYLESHEET, this.gameData);
        	this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }

    /**
     * Configures hard level button's action.
     */
    private void configureHardLevelButton() {
        LevelsDialogViewHelper.getInstance().getHardLevelButton().setOnMouseClicked(event -> {
            LoggingManager.getInstance().info("HARD LEVEL SELECTED");
        	this.gameData.setGameDifficulty(HARD_LEVEL);
        	this.sceneNavigator.startGame(Main.GAMEVIEW_ID,
            		Main.GAMEVIEW_URL,
            		Main.GAMEVIEW_STYLESHEET, this.gameData);
        	this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }

    /**
     * Sets visibility of levels panes.
     */
    private void manageDialogVisibility(){
        levelsPane.setVisible(false);
        levelsPane.toBack();
        levelsPane.requestFocus();
    }

    /**
     * Returns the root pane of the main menu.
     */
    public Node getRootPane() {
        return this.root;
    }
}
