package test;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javafx.scene.image.ImageView;
import model.characters.Character;

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

        // This will cause a Runtime exception as Internal Graphics are not initialized yet in an implicit way.

//        Character anas = CharacterFactory.getInstance().createCharacter("greenClown");
//        anas.instantiateCharacterControls();
//        System.out.println(anas);

        // This will cause a Runtime exception as Internal Graphics are not initialized yet in an implicit way.
        /*Character walid = new GreenClown();*/

        // This is the real cause of the Runtime exception explicitly as the Internal graphics are not initialized yet.
        /*ImageView image = new ImageView("File:src/assets/green_clown.png");*/

        // This form of initializing the ImageView with the no args constructor will not cause any abnormal behavior.
        ImageView imageLol = new ImageView();
        //System.out.println(anas);


    }
}