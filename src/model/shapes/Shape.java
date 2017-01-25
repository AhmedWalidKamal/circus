package model.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.Color;

public abstract class Shape {

	public enum State {
		CREATED, SLIDING, FALLING, FETCHED, NOT_FETCHED, IN_POOL;
	};

	protected State state = null;
	protected String url = "";
	protected double width = 0;
	protected double height = 0;
	protected Color color = null;
	protected DoubleProperty x = null;
	protected DoubleProperty y = null;
	protected DoubleProperty translateX = null;
	protected DoubleProperty translateY = null;

	public Shape() {
		state = State.CREATED;
		x = new SimpleDoubleProperty();
		y = new SimpleDoubleProperty();
		translateX = new SimpleDoubleProperty();
		translateY = new SimpleDoubleProperty();
	}

	public void setState(final State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(final double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(final double width) {
		this.width = width;
	}

	public double getX() {
		return x.intValue();
	}

	public void setX(final double x) {
		this.x.set(x);
	}

	public double getY() {
		return y.intValue();
	}

	public void setY(final double y) {
		this.y.set(y);
	}

	public DoubleProperty getTranslateX() {
		return translateX;
	}

	public DoubleProperty getTranslateY() {
		return translateY;
	}

	public DoubleProperty getXProperty() {
		return x;
	}

	public DoubleProperty getYProperty() {
		return y;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public abstract String getKey();
}
