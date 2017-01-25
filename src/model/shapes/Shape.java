package model.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.Color;

public abstract class Shape {

	public enum State {
		CREATED, SLIDING, FALLING, FETCHED, NOT_FETCHED, IN_POOL;

		@Override
		public String toString() {
			String ret = null;
			switch (this) {
				case CREATED:
					ret = "created";
					break;
				case SLIDING:
					ret = "sliding";
					break;
				case FALLING:
					ret = "falling";
					break;
				case FETCHED:
					ret = "fetched";
					break;
				case NOT_FETCHED:
					ret = "not_fetched";
					break;
				case IN_POOL:
					ret = "in_pool";
					break;
				default:
					break;
			}
			return ret;
		}
	};

	protected State state = null;
	protected String url = "";
	protected String key = "";
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

	public void setTranslateXProperty(final double translateX) {
		this.translateX.set(translateX);
	}

	public void setTranslateYProperty(final double translateY) {
		this.translateY.set(translateY);
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

	public String getKey() {
		return key;
	}

	public void setkey(final String key) {
		this.key = key;
	}
}
