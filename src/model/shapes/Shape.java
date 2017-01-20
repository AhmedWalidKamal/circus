package model.shapes;

import javafx.scene.image.ImageView;
import model.Color;

public interface Shape {

	public Color getColor();

	public void setColor(Color color);

	public void setX(double x);

	public void setY(double y);

	public double getX();

	public double getY();

	public double getWidth();

	public double getHeight();

	public ImageView getImageView();

	public void setImageView(ImageView shapeImageView);
}
