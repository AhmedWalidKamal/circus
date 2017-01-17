package model.characters.supportedCharacters;

import behaviour.keyBinding.KeyMap;
import behaviour.keyBinding.keyHandlers.AKeyHandler;
import behaviour.keyBinding.keyHandlers.DKeyHandler;
import javafx.scene.image.ImageView;
import model.characters.Character;

public class GreenClown implements Character {
    //might not be used at all.
    private static final String KEY = "grownClown";
    private static final String URL = "File:src/assets/green_clown.png";
    private static ImageView imageView = null;
    private KeyMap keyMap = null;

    public GreenClown() {
        imageView = new ImageView(URL);
        keyMap = new KeyMap(imageView);
        keyMap.addKeyHandler(new AKeyHandler());
        keyMap.addKeyHandler(new DKeyHandler());
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
        return imageView.getX();
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
}
