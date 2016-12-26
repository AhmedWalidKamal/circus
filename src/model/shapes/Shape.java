package model.shapes;

import javafx.scene.image.ImageView;
import model.Color;
import util.Point;

public interface Shape {

	public Color getColor();

	public void setColor(Color color);

	public Point getCenter();

	public void setCenter(Point center);

	public double getWidth();

	public double getHeight();

	public ImageView getImageView();

	public void setImageView(ImageView shapeImageView);
}
