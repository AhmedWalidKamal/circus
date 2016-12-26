package model.shapes.types;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.ImageView;
import model.Color;
import model.shapes.Plate;
import model.shapes.Shape;
import model.shapes.util.ShapeFactory;

public class PlateType implements ShapeType {

	private Map<Color, ImageView> imagesMap;
	private static final String KEY = "PLATE";
	static {
		ShapeFactory.getInstance().
		registerShape(KEY, PlateType.class);
	}
	public PlateType() {
		this.imagesMap = new HashMap<>();
	}
	@Override
	public Shape createShape(final Color color) {
		return new Plate(color);
	}

	@Override
	public void mapImageToColor(final Color color, final ImageView shapeImage) {
		imagesMap.put(color, shapeImage);
	}

	@Override
	public void mapImages(final Map<Color, ImageView> imagesMap) {
		this.imagesMap = imagesMap;
	}

}
