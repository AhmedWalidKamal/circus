package test;

import model.characters.Character;
import model.characters.util.CharacterFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestClassLoading {


    private static final String KEY_FIELD = "KEY";

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchFieldException {
        URLClassLoader loader = new URLClassLoader(
                new URL[]{new File("temp\\").toURI().toURL()});
        Class<?> loadedClass =  loader.loadClass("model.characters.supportedCharacters.GreenClown");
        Field keyField
                = loadedClass.getDeclaredField(KEY_FIELD);
        keyField.setAccessible(true);
        String str = (String) keyField.get(null);
        System.out.println(str);
        System.out.println(CharacterFactory.getInstance().getRegisteredCharacters());
        Character anas = CharacterFactory.getInstance().createCharacter("greenClown");
        System.out.println(anas);
    }
}