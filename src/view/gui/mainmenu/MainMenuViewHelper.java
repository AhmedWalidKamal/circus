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

    private MainMenuViewHelper() {

    }

    public static MainMenuViewHelper getInstance() {
        if (instance == null) {
            instance = new MainMenuViewHelper();
        }
        return  instance;
    }

    public void configureTheMainMenu(Pane root, Text title, SceneNavigator sceneNavigator) {


        MenuItem newGame = new MenuItem("NEW GAME");
        newGame.setOnMouseClicked(event -> {
            try {
                sceneNavigator.changeScene(Main.GAMEVIEW,
                        (Stage) root.getScene().getWindow(),
                        root.getScene().getWidth(),
                        root.getScene().getHeight(),
                        Main.GAMEVIEW_STYLESHEET
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        MenuItem exit = new MenuItem("EXIT");
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        MenuBox menu = new MenuBox(
                newGame,
                new MenuItem("LOAD GAME"),
                new MenuItem("OPTIONS"),
                new MenuItem("HELP"),
                exit);
        menu.setTranslateX(100);
        menu.setTranslateY(300);

        root.getChildren().add(menu);
        menu.setLayoutX(0);
        menu.setLayoutY(150);

        title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));

    }

}
