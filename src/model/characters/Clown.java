package model.characters;

import util.Score;
import model.shapes.Shape;
import util.Point;

import java.util.LinkedList;
import java.util.List;

public class Clown implements Character {

    private Point center;
    private Score score;
    private List<Shape> shapesList;
    private String characterKey;

    public Clown() {
        center = new Point();
        score = new Score();
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
    public Score getScore() {
        return this.score;
    }

    @Override
    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String getCharacterKey() {
        return this.characterKey;
    }

    @Override
    public void setCharacterKey(String characterKey) {
        this.characterKey = characterKey;
    }
}
