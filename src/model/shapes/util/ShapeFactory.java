package model.shapes.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;

import model.Color;
import model.shapes.Shape;
import model.shapes.types.ShapeType;

/**
 * A Singleton factory that is
 * responsible for creating shape
 * objects.
 * @author Ahmed Walid
 */
public class ShapeFactory {
	private static ShapeFactory factoryInstance = new
            ShapeFactory();
    private HashMap<String, Class<? extends ShapeType>>
			registeredShapes = null;

    /**
     * Instantiates a new shape factory.
     */
    private ShapeFactory() {
        registeredShapes = new HashMap<>();
    }

    /**
     * Gets the single instance of ShapeFactory.
     * @return single instance of ShapeFactory
     */
    public static ShapeFactory getInstance() {
        return factoryInstance;
    }

    /**
     * Register shape.
     * @param shapeID the shape id
     * @param shapeClass the shape class
     */
    public void registerShape(final String shapeID,
                                  final Class<? extends ShapeType>
										  shapeClass) {
        registeredShapes.put(shapeID, shapeClass);
    }

    /**
     * Creates a new Shape object.
     * @param shapeID the shape id
     * @return the shape object
     */
    public Shape createShape(final String shapeID, final Color color) {
        final Class<? extends ShapeType> shapeTypeClass =
                registeredShapes.get(shapeID);
        try {
            final Constructor<? extends ShapeType> shapeConstructor =
                    shapeTypeClass.getConstructor();
            return shapeConstructor.newInstance().createShape(color);
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException e) {
            return null;
        }
    }

    /**
     * Gets the registered shapes.
     * @return the registered shapes
     */
    public Collection<String> getRegisteredShapes() {
        return registeredShapes.keySet();
    }
}
