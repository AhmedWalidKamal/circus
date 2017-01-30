package model.characters.supported_characters;

import model.characters.Character;
import model.characters.util.CharacterFactory;

public class RedClown extends Character {
    public static final String KEY = "redClown";
    private static final String URL = "File:src/assets/characters/red_clown.png";
    private static final double LEFT_STACK_X_INSET = 50;
    private static final double RIGHT_STACK_X_INSET = 175;

    static {
        CharacterFactory.getInstance().registerCharacter(KEY, RedClown.class);
    }

    public RedClown() {
        super();
        super.leftStackXInset = LEFT_STACK_X_INSET;
        super.rightStackXInset = RIGHT_STACK_X_INSET;
        super.url = URL;
        super.key = KEY;
    }
}
