package model.characters.supportedCharacters;

import behaviour.keyBinding.KeyMap;
import behaviour.keyBinding.keyHandlers.LeftArrowKeyHandler;
import behaviour.keyBinding.keyHandlers.RightArrowKeyHandler;
import javafx.scene.image.ImageView;
import model.characters.Character;

public class RedClown implements Character {
    //might not be used at all.
    private static final String KEY = "redClown";
    private static final String URL = "File:src/assets/red_clown.png";
    private static ImageView imageView = null;
    private static KeyMap keyMap = null;

    public RedClown() {
        imageView = new ImageView(URL);
        keyMap = new KeyMap(imageView);
        keyMap.addKeyHandler(new RightArrowKeyHandler());
        keyMap.addKeyHandler(new LeftArrowKeyHandler());
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public double getY() {
        return imageView.getY();
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
}
