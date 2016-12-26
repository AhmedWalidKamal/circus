package util;

public class Point {
	private double x, y;

	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
}
