package model.characters.supportedCharacters;

import javafx.scene.image.ImageView;
import model.characters.Character;

public class GreenClown implements Character {
    //might not be used at all.
    private static final String KEY = "grownClown";
    private static final String URL = "File:src/assets/green_clown.png";
    private static ImageView imageView = null;

    public GreenClown() {
        imageView = new ImageView(URL);
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
}
