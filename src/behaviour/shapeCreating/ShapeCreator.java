package behaviour.shapeCreating;

import java.util.Random;

import javafx.scene.image.ImageView;
import model.Color;
import model.shapes.Shape;
import model.shapes.util.ShapeFactory;

public class ShapeCreator {

	private static final int SCREEN_WIDTH = 1000;
	private final ShapeCreatorHelper shapeCreatorHelper;

	public ShapeCreator() {
		shapeCreatorHelper = new ShapeCreatorHelper();
	}

	public Shape createShape() {
		final Color randomColor = Color.values()
				[genRandomNumber(Color.values().length)];
		final Object[] supportedShapes = ShapeFactory.
				getInstance().getRegisteredShapes().toArray();
		final String randomShape
		= (String)supportedShapes[genRandomNumber(supportedShapes.length)];
		final double initialX = genRandomNumber(SCREEN_WIDTH);
		final double initialY = 0;
		final String url = this.shapeCreatorHelper.getUrl(randomShape + "_" + randomColor);
		System.out.println(url);
		final Shape newShape = ShapeFactory.
				getInstance().createShape(randomShape, randomColor, new ImageView(url));
		newShape.setX(initialX);
		newShape.setY(initialY);
		return newShape;
	}

	private int genRandomNumber(final int upperBound) {
		return new Random().nextInt(upperBound);
	}
}
