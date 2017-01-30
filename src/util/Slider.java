package util;

import javafx.scene.image.ImageView;

public class Slider {
    private ImageView imageView = null;
    private static final String URL = "File:src/assets/sliders/slider.png";

    public enum Orientation {
        LEFT,
        RIGHT;
    }

    private Orientation orientation;

    public Slider(final double y, final Orientation orientation) {
        imageView = new ImageView(URL);
        imageView.setY(y);
        this.orientation = orientation;
    }

    public Slider() {
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

    public Orientation getOrientation() {
        return orientation;
    }
}
