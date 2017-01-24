package view.gui.mainmenu;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import view.gui.app.Main;
import view.gui.app.util.ControlledScenes;
import view.gui.app.util.ScenesNavigator;
import view.gui.levelsdialog.LevelsDialogViewHelper;
import view.gui.pausemenu.PauseMenuViewHelper;

public class MainMenuController implements Initializable, ControlledScenes {

    @FXML
    private Pane root;

    @FXML
    private Text title;

    @FXML
    private Pane levelsPane;

    @FXML
    private Text LevelsDialogTitle;

    private ScenesNavigator myController;


    private final static String EASY_LEVEL="EASY";
    private final static String MEDIUM_LEVEL="Medium";
    private final static String HARD_LEVEL="Hard";




    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
            root.setFocusTraversable(true);
            MainMenuViewHelper.getInstance().configureTheMainMenu(root, title);
            LevelsDialogViewHelper.getInstance().configureTheLevelsDialog(this.levelsPane,this.LevelsDialogTitle);
            setVisibilityBindingLevelsDialog();
            configureNewGameButton();
            configureExitButton();
            configureEasyLevelButton();
            configureMediumLevelButton();
            configureHardLevelButton();
    }

    @Override
    public void setScreenParent(final ScenesNavigator screenParent) {
    	this.myController = screenParent;
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
            myController.getMainController().setDifficultyLevel(EASY_LEVEL);
            this.myController.loadScreen(Main.GAMEVIEW_ID, Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET);
            this.myController.setScreen(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }
    private void configureMediumLevelButton() {
        LevelsDialogViewHelper.getInstance().getMediumLevelButton().setOnMouseClicked(event -> {
            myController.getMainController().setDifficultyLevel(MEDIUM_LEVEL);
            this.myController.loadScreen(Main.GAMEVIEW_ID, Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET);
            this.myController.setScreen(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }
    private void configureHardLevelButton() {
        LevelsDialogViewHelper.getInstance().getHardLevelButton().setOnMouseClicked(event -> {
            myController.getMainController().setDifficultyLevel(HARD_LEVEL);
            this.myController.loadScreen(Main.GAMEVIEW_ID, Main.GAMEVIEW_URL, Main.GAMEVIEW_STYLESHEET);
            this.myController.setScreen(Main.GAMEVIEW_ID);
            manageDialogVisibility();
        });
    }
    private void manageDialogVisibility(){
        levelsPane.setVisible(false);
        levelsPane.toBack();
        levelsPane.requestFocus();
    }
}
