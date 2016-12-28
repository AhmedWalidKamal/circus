package model;

import javafx.scene.image.ImageView;

public class Shelf {

	private ImageView shelfImage;
	public Shelf(final ImageView shelfImage, final double topleftX, final double topLeftY) {
		this.shelfImage = shelfImage;
		this.shelfImage.setX(topleftX);
		this.shelfImage.setY(topLeftY);
	}

	public void setImageView(final ImageView shelfImage) {
		this.shelfImage = shelfImage;
	}

	public ImageView getImageView() {
		return this.shelfImage;
	}

	public void setX(final double topLeftX) {
		this.shelfImage.setX(topLeftX);
	}

	public void setY(final double topLeftY) {
		this.shelfImage.setY(topLeftY);
	}

	public double getX() {
		return this.shelfImage.getX();
	}

	public double getY() {
		return this.shelfImage.getY();
	}
}
