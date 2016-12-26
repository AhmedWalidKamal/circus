package model.shapes.types;

import java.util.Map;

import javafx.scene.image.ImageView;
import model.Color;
import model.shapes.Shape;

public interface ShapeType {
	public Shape createShape(Color color);

	public void mapImageToColor(Color color, ImageView shapeImage);

	public void mapImages(Map<Color, ImageView> imagesMap);
}
