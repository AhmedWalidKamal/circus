package view.gui.app.util;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import view.gui.gameplay.GameViewController;
import view.gui.mainmenu.util.GameData;

/**
 * The parent pane that holds all scenes' panes inside it, also
 * connects all scenes with each other by keeping a map of loaded
 * scenes and setting the current scene whenever required.
 * @author Ahmed Walid
 */
public class ScenesNavigator extends StackPane {

	/**
	 * The map storing each scene by its name.
	 */
    private final Map<String, Node> scenes;

    /**
     * Initializes a new scenes navigator.
     */
    public ScenesNavigator() {
        super();
        this.scenes = new HashMap<String, Node>();
    }

    public void addScene(final String name, final Node screen) {
        scenes.put(name, screen);
    }

    public Node getScene(final String name) {
        return scenes.get(name);
    }

    /**
     * Loads the game view scene with its css file.
     * @param name, the name of the scene
     * @param resource, the url to the fxml file
     * @param stylesheet, the url to the css file
     * @param gameData, the data to be passed to the
     * game for initialzing
     */
    public void loadGame(final String name,
    		final String resource,
    		final String stylesheet, final GameData gameData) {
        try {
            final FXMLLoader myLoader
            = new FXMLLoader(getClass().getResource(resource));
            final Parent loadScreen
            = (Parent) myLoader.load();
            final GameViewController gameViewController
            = ((GameViewController) myLoader.getController());
            gameViewController.setScreenParent(this);
            gameViewController.startNewGame(gameData);
            loadScreen.getStylesheets().add(this.getClass().
            		getResource(stylesheet).toExternalForm());
            if (scenes.get(name) != null) {
            	scenes.remove(name);
            }
            addScene(name, loadScreen);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the required scene and adds it to the map/
     * @param name, the name of the scene
     * @param resource, the url to the fxml file
     * @param stylesheet, the url to the css file
     */
    public void loadScene(final String name,
    		final String resource, final String stylesheet) {
        try {
            final FXMLLoader myLoader
            = new FXMLLoader(getClass().getResource(resource));
            final Parent loadScreen
            = (Parent) myLoader.load();
            final ControlledScenes myScreenController
            = ((ControlledScenes) myLoader.getController());
            myScreenController.setScreenParent(this);
            loadScreen.getStylesheets().add(this.
            		getClass().
            		getResource(stylesheet).toExternalForm());
            if (scenes.get(name) != null) {
            	scenes.remove(name);
            }
            addScene(name, loadScreen);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the current scene from the top layer
     * and sets the scene with the required name
     * as the current scene.
     * @param name, the name of the scene to get on top
     */
	public void setScene(final String name) {
        if (scenes.get(name) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, scenes.get(name));
            } else {
                getChildren().add(scenes.get(name));
            }
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
        }
    }

	/**
	 * Unloads a scene from the top of the scenes' stack.
	 * @param name, the name of the scene to be unloaded.
	 */
    public void unloadScene(final String name) {
        if (scenes.remove(name) == null) {
            System.out.println("Screen didn't exist");
        }
    }
}
