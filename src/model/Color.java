package model;

public enum Color {
	RED,
	GREEN,
	BLUE,
	PURPLE;

	@Override
	public String toString() {
		String ret = null;
		switch (this) {
			case RED:
				ret = "red";
				break;
			case GREEN:
				ret = "green";
				break;
			case BLUE:
				ret = "blue";
				break;
			case PURPLE:
				ret = "purple";
				break;
			default:
				break;
		}
		return ret;
	}
}
