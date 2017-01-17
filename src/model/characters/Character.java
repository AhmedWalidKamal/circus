package model.characters;

import behaviour.keyBinding.KeyMap;
import javafx.scene.image.ImageView;

public interface Character {
    public ImageView getImageView();

    public double getY();

    public double getX();

    public void setY(double y);

    public void setX(double x);

    public String getKey();

    public KeyMap getKeyMap();

    public void setKeyMap(KeyMap keyMap);
}
