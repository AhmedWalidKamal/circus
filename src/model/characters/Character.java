package model.characters;

import model.shapes.Shape;
import util.Point;
import java.util.List;

public interface Character {

    public void setCenter (Point centerPoint);

    public Point getCenter();

    public List<Shape> getShapes();

    public void setShapes(List<Shape> shapesList);

    public void addShape(Shape shape);

    public String getCharacterKey();

    public Object[] getSupportedColors();
}
