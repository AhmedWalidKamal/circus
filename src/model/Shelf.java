package model;

import javafx.scene.image.ImageView;

public class Shelf {
    private ImageView imageView = null;
    /* TODO: Orientation -> right_to_left / left_to_right, according to
     * orientation, a shelf is set by AnchorPane.setAnchor, to right or left.*/
    private static final String URL = "File:src/assets/shelf.png";

    public Shelf(final double y) {
        imageView = new ImageView(URL);
        imageView.setY(y);
    }
    public Shelf() {
        imageView = new ImageView(URL);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getY() {
        return imageView.getY();
    }

    public void setY(final double y) {
        imageView.setY(y);
    }
}
