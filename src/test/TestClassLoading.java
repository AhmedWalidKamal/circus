package test;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import model.characters.util.CharacterFactory;

public class TestClassLoading {


    private static final String KEY_FIELD = "KEY";

    public static void main(final String[] args) throws MalformedURLException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchFieldException {
        final URLClassLoader loader = new URLClassLoader(
                new URL[]{new File("temp\\").toURI().toURL()});
        final Class<?> loadedClass =  loader.loadClass("model.characters.supportedCharacters.GreenClown");
        final Field keyField
                = loadedClass.getDeclaredField(KEY_FIELD);
        keyField.setAccessible(true);
        final String str = (String) keyField.get(null);
        System.out.println(str);
        System.out.println(CharacterFactory.getInstance().getRegisteredCharacters());
        //Character anas = CharacterFactory.getInstance().createCharacter("greenClown");
        //System.out.println(anas);
    }
}