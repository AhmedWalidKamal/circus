package model.shapes;

import javafx.scene.image.ImageView;
import model.Color;

public abstract class Shape {

	public enum State {
		CREATED, SLIDING, FALLING, FETCHED, NOT_FETCHED, IN_POOL;
	};

	private State state;

	public void setState(final State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public abstract Color getColor();

	public abstract void setColor(Color color);

	public abstract void setX(double x);

	public abstract void setY(double y);

	public abstract double getX();

	public abstract double getY();

	public abstract double getWidth();

	public abstract double getHeight();

	public abstract ImageView getImageView();

	public abstract void setImageView(ImageView shapeImageView);
}
