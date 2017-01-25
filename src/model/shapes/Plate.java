package model.shapes;

import javafx.scene.image.ImageView;
import model.Color;
import model.shapes.util.ShapeFactory;

public class Plate extends Shape {
	private Color plateColor;
	private static final String KEY = "PLATE";

	static {
		ShapeFactory.getInstance().registerShape(KEY, Plate.class);
	}

	public Plate(Color color) {
		super();
		super.color = color;
	}

	@Override
	public String getKey() {
		return KEY;
	}
}
