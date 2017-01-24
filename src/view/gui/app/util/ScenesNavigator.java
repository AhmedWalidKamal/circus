package view.gui.app.util;

import java.util.HashMap;
import java.util.Map;

import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScenesNavigator extends StackPane {

    private final Map<String, Node> scenes;
    private final MainController mainController;

    public ScenesNavigator() {
        super();
        this.scenes = new HashMap<String, Node>();
        this.mainController = new MainController();
    }

    public MainController getMainController() {
    	return this.mainController;
    }

    public void addScene(final String name, final Node screen) {
        scenes.put(name, screen);
    }

    public Node addScene(final String name) {
        return scenes.get(name);
    }

    public boolean loadScreen(final String name, final String resource, final String stylesheet) {
        try {
            final FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            final Parent loadScreen = (Parent) myLoader.load();
            final ControlledScenes myScreenController = ((ControlledScenes) myLoader.getController());
            myScreenController.setScreenParent(this);
            loadScreen.getStylesheets().add(this.getClass().getResource(stylesheet).toExternalForm());
            addScene(name, loadScreen);
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setScreen(final String name) {
        if (scenes.get(name) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, scenes.get(name));
            } else {
                getChildren().add(scenes.get(name));
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }


        /*Node screenToRemove;
         if(screens.get(name) != null){   //screen loaded
         if(!getChildren().isEmpty()){    //if there is more than one screen
         getChildren().add(0, screens.get(name));     //add the screen
         screenToRemove = getChildren().get(1);
         getChildren().remove(1);                    //remove the displayed screen
         }else{
         getChildren().add(screens.get(name));       //no one else been displayed, then just show
         }
         return true;
         }else {
         System.out.println("screen hasn't been loaded!!! \n");
         return false;
         }*/
    }

    public boolean unloadScene(final String name) {
        if (scenes.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}
