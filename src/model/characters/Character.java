package model.characters;

import util.Score;
import model.shapes.Shape;
import util.Point;
import java.util.List;

public interface Character {

    public void setCenter (Point centerPoint);

    public Point getCenter();

    public List<Shape> getShapes();

    public void setShapes(List<Shape> shapesList);

    public void addShape(Shape shape);

    public Score getScore();

    public void setScore(Score score);

    public String getCharacterKey();

    public void setCharacterKey(String key);

}
