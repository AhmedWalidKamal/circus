package view.gui.mainmenu;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.gui.app.Main;
import view.gui.app.util.SceneNavigator;



public class MainMenuController implements Initializable {
	/**
     * Root pane.
     */
    @FXML
    private StackPane root;

    /**
     * Button to start a new game.
     */
    @FXML
    private StackPane newGame;

    @FXML
    private StackPane loadGame;

    @FXML
    private StackPane options;

    @FXML
    private StackPane help;

    @FXML
    private StackPane exit;


    /**
     * Scene Navigator objects that allows changing the scene.
     */
    private SceneNavigator sceneNavigator = null;

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        root.setFocusTraversable(true);
        sceneNavigator = new SceneNavigator();

            try {
                menuButtonMouseNewGameConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }

            menuButtonMouseLoadGameConfig();
            menuButtonMouseOptionsConfig();
            menuButtonMouseHelpConfig();
            menuButtonMouseExitConfig();
        }



    private void menuButtonMouseNewGameConfig() throws IOException {
        newGame.setOnMouseClicked(event -> {
            List<Node> newGameChildrenObjects = newGame.getChildren();
            MainMenuViewHelper.getInstance().buttonPressed(newGameChildrenObjects);
            try {
                sceneNavigator.changeScene(Main.GAMEVIEW,
                        (Stage) this.newGame.getScene().getWindow(),
                        this.newGame.getScene().getWidth(),
                        this.newGame.getScene().getHeight(),
                        Main.GAMEVIEW_STYLESHEET
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        menuButtonMouseEntered(newGame);
        menuButtonMouseExited(newGame);
        menuButtonMouseReleased(newGame);
    }

    private void menuButtonMouseLoadGameConfig() {
        loadGame.setOnMouseClicked(event -> {
            List<Node> loadGameChildrenObjects = loadGame.getChildren();
        MainMenuViewHelper.getInstance().buttonPressed(loadGameChildrenObjects);
    });


        menuButtonMouseEntered(loadGame);
        menuButtonMouseExited(loadGame);
        menuButtonMouseReleased(loadGame);
        //TODO
        //to be continued..
    }

    private void menuButtonMouseOptionsConfig() {
        options.setOnMouseClicked(event -> {
            List<Node> optionsChildrenObjects = options.getChildren();
            MainMenuViewHelper.getInstance().buttonPressed(optionsChildrenObjects);
        });

        menuButtonMouseEntered(options);
        menuButtonMouseExited(options);
        menuButtonMouseReleased(options);
        //TODO
        //to be continued..
    }

    private void menuButtonMouseHelpConfig() {
        help.setOnMouseClicked(event -> {
            List<Node> optionsChildrenObjects = help.getChildren();
            MainMenuViewHelper.getInstance().buttonPressed(optionsChildrenObjects);

        });

        //TODO
        //to be continued..

        menuButtonMouseEntered(help);
        menuButtonMouseExited(help);
        menuButtonMouseReleased(help);
    }


    private void menuButtonMouseExitConfig() {
        exit.setOnMouseClicked(event1 -> {
            List<Node> exitChildrenObjects = exit.getChildren();
            MainMenuViewHelper.getInstance().buttonPressed(exitChildrenObjects);
            System.exit(0);
        });

        menuButtonMouseEntered(exit);
        menuButtonMouseExited(exit);
        menuButtonMouseReleased(exit);

    }


    private void menuButtonMouseEntered(StackPane stackpane) {
        stackpane.setOnMouseEntered(event -> MainMenuViewHelper.getInstance().
                buttonEntered(getComponentChildren(event)));


    }


    private void menuButtonMouseExited(StackPane stackPane) {
        stackPane.setOnMouseExited(event -> MainMenuViewHelper.getInstance().
                buttonExited(getComponentChildren(event)));


    }


    private void menuButtonMouseReleased(StackPane stackPane) {
        stackPane.setOnMouseReleased(event -> MainMenuViewHelper.getInstance().
                buttonReleased(getComponentChildren(event)));


    }


    private List<Node> getComponentChildren(final MouseEvent event) {
        List<Node> childrenObjects;
        if (event.getSource() == newGame) {
            childrenObjects = newGame.getChildren();
        } else if (event.getSource() == loadGame) {
            childrenObjects = loadGame.getChildren();
        } else if (event.getSource() == options) {
            childrenObjects = options.getChildren();
        } else if (event.getSource() == help) {
            childrenObjects = help.getChildren();
        } else {
            childrenObjects = exit.getChildren();
        }

        return childrenObjects;
    }



}
