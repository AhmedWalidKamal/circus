package model.characters.supportedCharacters;

import behaviour.keyBinding.KeyMap;
import behaviour.keyBinding.keyHandlers.AKeyHandler;
import behaviour.keyBinding.keyHandlers.DKeyHandler;
import javafx.scene.image.ImageView;
import model.characters.Character;
import model.characters.util.CharacterFactory;

public class RedClown implements Character {

    private static String KEY = "redClown";
    private static final String URL = "File:src/assets/red_clown.png";
    private ImageView imageView;
    private KeyMap keyMap;

    public RedClown() {

    }

    static {
        CharacterFactory.getInstance().registerCharacter(KEY,RedClown.class);
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
        return keyMap;
    }

    @Override
    public void setKeyMap(final KeyMap keyMap) {
        this.keyMap = keyMap;
    }

    @Override
    public void instantiateCharacterControls() {
        imageView = new ImageView(URL);
        keyMap = new KeyMap(imageView);
        keyMap.addKeyHandler(new AKeyHandler());
        keyMap.addKeyHandler(new DKeyHandler());
    }
}
