package model.characters.supportedCharacters;

import behaviour.keyBinding.KeyMap;
import behaviour.keyBinding.keyHandlers.AKeyHandler;
import behaviour.keyBinding.keyHandlers.DKeyHandler;
import behaviour.keyBinding.keyHandlers.LeftArrowKeyHandler;
import behaviour.keyBinding.keyHandlers.RightArrowKeyHandler;
import javafx.scene.image.ImageView;
import model.characters.Character;
import model.characters.util.CharacterFactory;

public class GreenClown implements Character {

    private static String KEY = "greenClown";
    private static final String URL = "File:src/assets/green_clown.png";
    private ImageView imageView;
    private KeyMap keyMap;

    public GreenClown() {

    }

    static {
        CharacterFactory.getInstance().registerCharacter(KEY, GreenClown.class);
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public double getY() {
        return imageView.getY() + imageView.getTranslateY() + imageView.getLayoutY();
    }

    @Override
    public double getX() {
        return imageView.getX() + imageView.getTranslateX();
    }

    @Override
    public void setY(final double y) {
        imageView.setY(y);
    }

    @Override
    public void setX(final double x) {
        imageView.setX(x);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public KeyMap getKeyMap() {
        return this.keyMap;
    }

    @Override
    public void setKeyMap(final KeyMap keyMap) {
        this.keyMap = keyMap;
    }

    @Override
    public void instantiateCharacterControls() {
        imageView = new ImageView(URL);
        keyMap = new KeyMap(imageView);
        keyMap.addKeyHandler(new LeftArrowKeyHandler());
        keyMap.addKeyHandler(new RightArrowKeyHandler());
    }
}
