package plugins;

import logs.LogsManager;
import model.characters.Character;
import model.shapes.Shape;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    private static final String JAR_EXTENSION = ".jar";
    private static final String PACKAGE_NAME_CHAR = "model.characters." +
            "supportedCharacters.";
    private static final String PACKAGE_NAME_SHAPE = "model.shapes.";
    private static PluginLoader instance;
    private static final String CLASS_EXTENSION = ".class";
    private static final String PACKAGE_DIR_CHAR = "model\\character\\supportedCharacters\\";
    private static final String PACKAGE_DIR_SHAPE = "model\\shapes\\";

    private PluginLoader () {

    }

    public static PluginLoader getInstance() {
        if (instance == null) {
            instance = new PluginLoader();
        }
        return instance;
    }

    public void load(String key, File file) {
        if (file == null) {
            //TODO Handle the exception in a cleaner way.
            throw new RuntimeException();
        }
        try{
            String classFile = file.getName();
            String className = "";
            String filePath = file.getPath();
            if (classFile.endsWith(CLASS_EXTENSION)) {

                className = classFile.replaceAll(CLASS_EXTENSION,"");

                if (filePath.contains(PACKAGE_DIR_CHAR)) {
                    file = new File(filePath.substring(filePath.
                            indexOf(PACKAGE_DIR_CHAR)));
                } else if (filePath.contains(PACKAGE_DIR_SHAPE)) {
                    file = new File(filePath.substring(filePath.
                            indexOf(PACKAGE_DIR_SHAPE)));
                }
            }
            if (classFile.endsWith(JAR_EXTENSION)) {
                className = classFile.replaceAll(JAR_EXTENSION,"");
            }
            URLClassLoader loader = URLClassLoader.newInstance(
                    new URL[]{file.toURI().toURL() });

            if (key.equalsIgnoreCase("character")) {
                Class<?> loadedClass = loader.loadClass(PACKAGE_NAME_CHAR + className);
                if (Character.class.isAssignableFrom(loadedClass)) {
                    Character.class.cast(loadedClass);
                } else {
                    //TODO Handle the exception of not following the Character Interface for supported characters.
                    throw new RuntimeException();
                }
            } else {
                Class<?> loadedClass = loader.loadClass(PACKAGE_NAME_SHAPE + className);
                if (Shape.class.isAssignableFrom(loadedClass)) {
                    Shape.class.cast(loadedClass);
                } else {
                    //TODO Handle the exception of not following the Shape Interface for supported shapes.
                    throw new RuntimeException();
                }
            }

            loader.close();
        } catch (ClassNotFoundException | IOException e) {
            LogsManager.getInstance().info("FAILED TO LOAD CLASS");
            e.printStackTrace();
        }
    }


    //TODO Test this method.
}
