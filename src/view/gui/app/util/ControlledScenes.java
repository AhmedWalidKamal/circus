package view.gui.app.util;

/**
 * The interface responsible for scene naviation, any
 * scene implementing this interface will be able to
 * refer to its parent to change the scene.
 * @author Ahmed Walid
 */
public interface ControlledScenes {
	public void setScreenParent(ScenesNavigator screenParent);
}
