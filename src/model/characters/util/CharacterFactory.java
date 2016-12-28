package model.characters.util;

import model.Color;
import model.characters.Character;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;

public class CharacterFactory {
    private static CharacterFactory factoryInstance = new
            CharacterFactory();
    private HashMap<String, Class<? extends Character>>
            registeredCharacters = null;

    /**
     * Instantiates a new character factory.
     */
    private CharacterFactory() {
        registeredCharacters = new HashMap<>();
    }

    /**
     * Gets the single instance of CharacterFactory.
     * @return single instance of CharacterFactory
     */
    public static CharacterFactory getInstance() {
        return factoryInstance;
    }

    /**
     * Register character.
     * @param CharacterID the character id
     * @param characterClass the character class
     */
    public void registerCharacter(final String CharacterID,
                              final Class<? extends Character>
                                      characterClass) {
        registeredCharacters.put(CharacterID, characterClass);
    }

    /**
     * Creates a new Character object.
     * @param CharacterID the character id
     * @return the character object
     */
    public Character createCharacter(final String CharacterID, final Color color) {
        final Class<? extends Character> characterTypeClass =
                registeredCharacters.get(CharacterID);
        try {
            final Constructor<? extends Character> characterConstructor =
                    characterTypeClass.getConstructor();
            return characterConstructor.newInstance();
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException e) {
            return null;
        }
    }

    /**
     * Gets the registered characters.
     * @return the registered characters
     */
    public Collection<String> getRegisteredCharacters() {
        return registeredCharacters.keySet();
    }
}
