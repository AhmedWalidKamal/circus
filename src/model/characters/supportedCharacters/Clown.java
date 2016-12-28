package model.characters.supportedCharacters;

import model.characters.Character;
import model.shapes.Shape;
import util.Point;

import java.util.LinkedList;
import java.util.List;

public class Clown implements Character {

    private Point center;
    private List<Shape> shapesList;
    private static final String KEY = "clown";

    public Clown() {
        center = new Point();
        shapesList = new LinkedList<>();
    }

    @Override
    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public List<Shape> getShapes() {
        return this.shapesList;
    }

    @Override
    public void setShapes(List<Shape> shapesList) {
        this.shapesList = shapesList;
    }

    @Override
    public void addShape(Shape shape) {
        this.shapesList.add(shape);
    }

    @Override
    public String getCharacterKey() {
        return this.KEY;
    }

    @Override
    public Object[] getSupportedColors() {
        return ClownColor.values();
    }

}
