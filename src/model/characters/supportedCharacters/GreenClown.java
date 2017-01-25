package model.characters.supportedCharacters;

import model.characters.Character;
import model.characters.util.CharacterFactory;

public class GreenClown extends Character {
    public static final String KEY = "greenClown";
    private static final String URL = "File:src/assets/green_clown.png";
    private static final double LEFT_STACK_X_INSET = 50;
    private static final double RIGHT_STACK_X_INSET = 175;

    static {
        CharacterFactory.getInstance().registerCharacter(KEY, GreenClown.class);
    }

    public GreenClown() {
        super();
        super.leftStackXInset = LEFT_STACK_X_INSET;
        super.rightStackXInset = RIGHT_STACK_X_INSET;
        super.url = URL;
        super.key = KEY;
    }
}