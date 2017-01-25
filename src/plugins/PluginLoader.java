package plugins;



import logs.LoggingManager;
import model.characters.util.CharacterFactory;
import model.shapes.util.ShapeFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    private static final String JAR_EXTENSION = ".jar";
    private static final String PACKAGE_NAME_CHAR = "model.characters." +
            "supportedCharacters.";
    private static final String PACKAGE_NAME_SHAPE = "model.shapes.";
    private static final String CLASS_EXTENSION = ".class";
    private static final String PACKAGE_DIR_CHAR = "model\\character\\supportedCharacters\\";
    private static final String PACKAGE_DIR_SHAPE = "model\\shapes\\";
    private static PluginLoader instance;

    private PluginLoader () {

    }

    public static PluginLoader getInstance() {
        if (instance == null) {
            instance = new PluginLoader();
        }
        return instance;
    }

    public void load(String key, File file) {
        if (file != null) {
            try {
                String classFile = file.getName();
                String className = "";
                String filePath = file.getPath();
                if (classFile.endsWith(CLASS_EXTENSION)) {

                    className = classFile.replaceAll(CLASS_EXTENSION, "");

                    if (filePath.contains(PACKAGE_DIR_CHAR)) {
                        file = new File(filePath.substring(0,filePath.
                                indexOf(PACKAGE_DIR_CHAR)));
                    } else if (filePath.contains(PACKAGE_DIR_SHAPE)) {
                        file = new File(filePath.substring(0, filePath.
                                indexOf(PACKAGE_DIR_SHAPE)));
                    }
                }
                if (classFile.endsWith(JAR_EXTENSION)) {
                    className = classFile.replaceAll(JAR_EXTENSION, "");
                }
                URLClassLoader loader = URLClassLoader.newInstance(
                        new URL[]{file.toURI().toURL()});
                if (key.equalsIgnoreCase("character")) {
                    @SuppressWarnings("unchecked")
                    Class<? extends model.characters.Character> loadedClass = (Class<? extends model.characters.Character>) (loader.
                            loadClass(PACKAGE_NAME_CHAR + className));
                    Field field = loadedClass.getDeclaredField("KEY");
                    field.setAccessible(true);
                    System.out.println(field.get(null));
                    System.out.println(CharacterFactory.getInstance().getRegisteredCharacters());
                } else {
                    @SuppressWarnings("unchecked")
                    Class<? extends model.shapes.Shape> loadedClass = (Class<? extends model.shapes.Shape>) (loader.
                            loadClass(PACKAGE_NAME_SHAPE + className));
                    Field field = loadedClass.getDeclaredField("KEY");
                    field.setAccessible(true);
                    System.out.println(field.get(null));
                    System.out.println(ShapeFactory.getInstance().getRegisteredShapes());

                }


                loader.close();
            } catch (ClassNotFoundException
                    | IOException
                    | IllegalAccessException
                    | NoSuchFieldException e) {
                LoggingManager.getInstance().info("FAILED TO LOAD CLASS");
                e.printStackTrace();
            }
        }
    }


    //TODO Test this method.
}