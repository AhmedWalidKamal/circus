package model.characters.supportedCharacters;

import javafx.scene.image.ImageView;
import model.characters.Character;

public class RedClown implements Character {
    //might not be used at all.
    private static final String KEY = "redClown";
    private static final String URL = "File:src/assets/red_clown.png";
    private static ImageView imageView = null;

    public RedClown() {
        imageView = new ImageView(URL);
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }
}
