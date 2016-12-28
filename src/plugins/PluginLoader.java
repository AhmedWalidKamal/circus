package plugins;

import model.characters.Character;
import model.shapes.Shape;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    private static final String CHARACTER = "character";
    private static final String SHAPE = "shape";
    private static final String JAR_EXTENSION = ".jar";
    private static final String PACKAGE_NAME_CHAR = "model.characters." +
            "supportedCharacters.";
    private static final String PACKAGE_NAME_SHAPE = "model.shapes.";

    public static String load(String key, File file) {
        if (file == null) {
            return null;
        }
        try{
            String classFile = file.getName();
            String className = "";

            if (classFile.endsWith(JAR_EXTENSION)) {
                className = classFile.replaceAll(JAR_EXTENSION,"");
            }
            URLClassLoader loader = URLClassLoader.newInstance(
                    new URL[]{file.toURI().toURL() });
            if (key.equalsIgnoreCase(CHARACTER)
                    && Character.class.isAssignableFrom(
                            loader.loadClass(PACKAGE_NAME_CHAR + className))) {
                  return CHARACTER;
            } else {
                if (Shape.class.isAssignableFrom(
                            loader.loadClass(PACKAGE_NAME_SHAPE + className))) {
                    return SHAPE;
                }
            }
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    //TODO Test this method + support .class files
}
