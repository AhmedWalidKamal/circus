package behaviour.shapes.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ShapeCreatorHelper {
	private static final String BUNDLE_NAME
	= "behaviour.shapes.shape-images";

	private final ResourceBundle resourceBundle;

	public ShapeCreatorHelper() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public String getUrl(final String key) {
		try {
			return resourceBundle.getString(key);
		} catch (final MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
