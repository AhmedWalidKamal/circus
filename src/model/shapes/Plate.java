package model.shapes;

import javafx.scene.image.ImageView;
import model.Color;
import model.shapes.types.PlateType;
import util.Point;

public class Plate extends PlateType implements Shape {

	private Point center;
	private ImageView plateImage;
	private Color plateColor;

	public Plate() {
		center = new Point();
		plateImage = new ImageView();
	}

	public Plate(final Color color) {
		center = new Point();
		plateImage = new ImageView();
		this.plateColor = color;
	}

	@Override
	public Point getCenter() {
		return this.center;
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
	public void setCenter(final Point center) {
		this.center = center;
	}
}
