package model.shapes;

import model.Color;
import model.shapes.util.ShapeFactory;

public class Lego extends Shape {
    private static final String KEY = "LEGO";

    static {
        ShapeFactory.getInstance().registerShape(KEY, Lego.class);
    }

    public Lego(final Color color) {
        super();
        super.color = color;
        super.key = KEY;
    }
}
