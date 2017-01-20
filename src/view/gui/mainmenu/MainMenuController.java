package view.gui.mainmenu;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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

    private MainMenuViewHelper mainMenuViewHelper;

    private static boolean flag = false;

    private static final Color MOUSE_ENTERED_TEXT_COLOR = Color.WHITE;
    private static final Color MOUSE_EXITED_TEXT_COLOR = Color.DARKGRAY;
    private static final Color MOUSE_EXITED_REC_COLOR = Color.BLACK;
    private static final Color MOUSE_PRESSED_REC_COLOR = Color.DARKVIOLET;


    /**
     * Scene Navigator objects that allows changing the scene.
     */
    private SceneNavigator sceneNavigator = null;

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        root.setFocusTraversable(true);
        sceneNavigator = new SceneNavigator();
        mainMenuViewHelper = new MainMenuViewHelper();

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
            buttonPressed(newGameChildrenObjects);
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
        buttonPressed(loadGameChildrenObjects);
    });

        loadGame.setOnMouseEntered(event -> {

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
            buttonPressed(optionsChildrenObjects);
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
            buttonPressed(optionsChildrenObjects);
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
            buttonPressed(exitChildrenObjects);
            System.exit(0);
        });

        menuButtonMouseEntered(exit);
        menuButtonMouseExited(exit);
        menuButtonMouseReleased(exit);

    }


    private void menuButtonMouseEntered(StackPane stackpane) {
        stackpane.setOnMouseEntered(event -> buttonEntered(getComponentChildren(event)));

    }


    private void menuButtonMouseExited(StackPane stackPane) {
        stackPane.setOnMouseExited(event -> buttonExited(getComponentChildren(event)));

    }


    private void menuButtonMouseReleased(StackPane stackPane) {
        stackPane.setOnMouseReleased(event -> buttonReleased(getComponentChildren(event)));

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

    void buttonPressed(List<Node> childrenObjects) {
        Rectangle rect;
        if (childrenObjects.get(0) instanceof Rectangle) {
            rect = (Rectangle) childrenObjects.get(0);
        } else {
            rect = (Rectangle) childrenObjects.get(1);
        }
        rect.setFill(MOUSE_PRESSED_REC_COLOR);
    }

    void buttonEntered(List<Node> childrenObjects) {
        Rectangle rect;
        Text txt;
        if (childrenObjects.get(0) instanceof Rectangle) {
            rect = (Rectangle) childrenObjects.get(0);
            txt = (Text) childrenObjects.get(1);
        } else {
            rect = (Rectangle) childrenObjects.get(1);
            txt = (Text) childrenObjects.get(0);
        }
//        rect.setFill(gradient);
        txt.setFill(MOUSE_ENTERED_TEXT_COLOR);
    }

    void buttonExited(List<Node> childrenObjects) {
        Rectangle rect;
        Text txt;
        if (childrenObjects.get(0) instanceof Rectangle) {
            rect = (Rectangle) childrenObjects.get(0);
            txt = (Text) childrenObjects.get(1);
        } else {
            rect = (Rectangle) childrenObjects.get(1);
            txt = (Text) childrenObjects.get(0);
        }
        rect.setFill(MOUSE_EXITED_REC_COLOR);
        txt.setFill(MOUSE_EXITED_TEXT_COLOR);

    }

    void buttonReleased(List<Node> childrenObjects) {
        Rectangle rect;
        if (childrenObjects.get(0) instanceof Rectangle) {
            rect = (Rectangle) childrenObjects.get(0);
        } else {
            rect = (Rectangle) childrenObjects.get(1);
        }
//        rect.setFill(gradient);
    }

}
