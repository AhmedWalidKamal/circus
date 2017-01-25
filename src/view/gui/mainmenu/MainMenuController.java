package view.gui.mainmenu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import view.gui.app.Main;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;
import view.gui.levelsdialog.LevelsDialogViewHelper;
import view.gui.mainmenu.util.GameData;

public class MainMenuController implements Initializable, ControlledScenes {

    @FXML
    private Pane root;

    @FXML
    private Text title;

    @FXML
    private Pane levelsPane;

    @FXML
    private Text LevelsDialogTitle;


    private ScenesNavigator sceneNavigator;

    private final static String EASY_LEVEL = "EASY";
    private final static String MEDIUM_LEVEL = "MEDIUM";
    private final static String HARD_LEVEL = "HARD";

    private GameData gameData;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
            root.setFocusTraversable(true);
            MainMenuViewHelper.getInstance().
            configureTheMainMenu(root, title);
            LevelsDialogViewHelper.
            getInstance().configureTheLevelsDialog(this.levelsPane,
            		this.LevelsDialogTitle);
            setVisibilityBindingLevelsDialog();
            gameData = new GameData();
            configureNewGameButton();
            configureExitButton();
            configureEasyLevelButton();
            configureMediumLevelButton();
            configureHardLevelButton();
    }

    @Override
    public void setScreenParent(final ScenesNavigator screenParent) {
    	this.sceneNavigator = screenParent;
    }

    private void setVisibilityBindingLevelsDialog() {
        levelsPane.managedProperty().bind(levelsPane.visibleProperty());
        levelsPane.setVisible(false);
    }

    private void configureNewGameButton() {
        MainMenuViewHelper.getInstance().getNewGameButton().setOnMouseClicked(event -> {
            levelsPane.setVisible(true);
            levelsPane.toFront();
            levelsPane.requestFocus();
        });
    }

    private void configureLoadGameButton() {

    }

    private void configureExitButton() {
        MainMenuViewHelper.getInstance().getExitButton().setOnMouseClicked(event -> System.exit(0));
    }

    private void configureEasyLevelButton() {
        LevelsDialogViewHelper.getInstance().getEasyLevelButton().setOnMouseClicked(event -> {
            this.gameData.setGameDifficulty(EASY_LEVEL);
            this.sceneNavigator.loadGame(Main.GAMEVIEW_ID,
            		Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET, this.gameData);
            this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }
    private void configureMediumLevelButton() {
        LevelsDialogViewHelper.getInstance().getMediumLevelButton().setOnMouseClicked(event -> {
        	this.gameData.setGameDifficulty(MEDIUM_LEVEL);
        	this.sceneNavigator.loadGame(Main.GAMEVIEW_ID,
            		Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET, this.gameData);
        	this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }
    private void configureHardLevelButton() {
        LevelsDialogViewHelper.getInstance().getHardLevelButton().setOnMouseClicked(event -> {
        	this.gameData.setGameDifficulty(HARD_LEVEL);
        	this.sceneNavigator.loadGame(Main.GAMEVIEW_ID,
            		Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET, this.gameData);
        	this.sceneNavigator.setScene(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }
    private void manageDialogVisibility(){
        levelsPane.setVisible(false);
        levelsPane.toBack();
        levelsPane.requestFocus();
    }
}
