package model.shapes;

import javafx.beans.InvalidationListener;
import javafx.scene.image.ImageView;
import model.Color;
import model.shapes.util.ShapeFactory;

public class Plate extends Shape {

	private ImageView plateImage;

	private Color plateColor;

	private static final String KEY = "PLATE";

	static {
		ShapeFactory.getInstance().registerShape(KEY, Plate.class);
	}

	public Plate() {
		this.plateImage = new ImageView();
	}

	public Plate(final Color color, final ImageView plateImage) {
		this.plateImage = plateImage;
		this.plateColor = color;
	}

	@Override
	public double getWidth() {
		return this.plateImage.getFitWidth();
	}

	@Override
	public double getHeight() {
		return this.plateImage.getFitHeight();
	}

	@Override
	public Color getColor() {
		return this.plateColor;
	}

	@Override
	public void setColor(final Color color) {
		this.plateColor = color;
	}

	@Override
	public ImageView getImageView() {
		return this.plateImage;
	}

	@Override
	public void setImageView(final ImageView shapeImageView) {
		this.plateImage = shapeImageView;
	}

	@Override
	public void setX(final double topLeftX) {
		this.plateImage.setX(topLeftX);
	}

	@Override
	public void setY(final double topLeftY) {
		this.plateImage.setY(topLeftY);
	}

	@Override
	public double getX() {
		return this.plateImage.getX();
	}

	@Override
	public double getY() {
		return this.plateImage.getY();
	}
}
