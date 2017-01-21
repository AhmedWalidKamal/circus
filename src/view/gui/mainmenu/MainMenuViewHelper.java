package view.gui.mainmenu;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

public class MainMenuViewHelper {

    private LinearGradient gradient;
    private static final Color MOUSE_ENTERED_TEXT_COLOR = Color.WHITE;
    private static final Color MOUSE_EXITED_TEXT_COLOR = Color.DARKGRAY;
    private static final Color MOUSE_EXITED_REC_COLOR = Color.BLACK;
    private static final Color MOUSE_PRESSED_REC_COLOR = Color.DARKVIOLET;
    private static MainMenuViewHelper mainMenuViewHelperInstance = null;

   MainMenuViewHelper() {

       gradient  =  new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
               new Stop(0, Color.DARKVIOLET),
               new Stop(0.1, Color.BLACK),
               new Stop(0.9, Color.BLACK),
               new Stop(1, Color.DARKVIOLET));
    }

   static MainMenuViewHelper getInstance() {
        if (mainMenuViewHelperInstance == null) {
            mainMenuViewHelperInstance = new MainMenuViewHelper();
        }
        return  mainMenuViewHelperInstance;
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
        rect.setFill(gradient);
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
        rect.setFill(gradient);
    }

}
