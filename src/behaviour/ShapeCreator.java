package behaviour;

import javafx.scene.image.ImageView;
import model.Color;
import model.shapes.Shape;
import model.shapes.util.ShapeFactory;
import util.Point;

import java.util.Random;

public class ShapeCreator {

	public Shape createShape() {
		final Color[] supportedColors = Color.values();
		final Random rand = new Random();
		final int ind = rand.nextInt(supportedColors.length);
		final Color currentColor = supportedColors[ind];
		final String shapeIdentifier = "PLATE";
		final double initialX = 10.6;
		final double initalY = 0;
		final Shape newShape = ShapeFactory.
				getInstance().createShape(shapeIdentifier, currentColor);
		newShape.setCenter(new Point(initialX, initalY));
		return newShape;
	}

	public static void main(final String[] args) {

		try {
			Class.forName("model.shapes.types.PlateType");
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		final ShapeCreator x = new ShapeCreator();
		final Shape a = x.createShape();
		System.out.println(a.getColor());
	}
}
