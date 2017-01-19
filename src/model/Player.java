package model;

import model.characters.Character;
import model.shapes.Shape;
import util.Score;

import java.util.Stack;

public class Player implements Comparable<Player> {

    private Score score;
    private String name;
    private Character character;
    private Stack<Shape> leftStack = null;
    private Stack<Shape> rightStack = null;

    //TODO: Separate this stuff in Character class.
    private double leftStackXInset = 50;
    private double rightStackXInset = 175;
    private double leftStackYInset = 0;
    private double rightStackYInset = 0;

    public Player() {
        score = new Score();
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return this.name;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return this.score;
    }

    public Character getCharacter() {
        return this.character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public int compareTo(Player player) {
        if (score.compareTo(player.getScore()) != 0) {
            return score.compareTo(player.getScore());
        } else {
            return name.compareTo(player.getPlayerName());
        }
    }

    public void addToLeftStack(Shape shape) {
        leftStack.add(shape);
        leftStackYInset += shape.getImageView().getImage().getHeight();
    }

    public void addToRightStack(Shape shape) {
        rightStack.add(shape);
        rightStackYInset += shape.getImageView().getImage().getHeight();
    }

    public double getLeftStackXInset() {
        return leftStackXInset;
    }

    public double getLeftStackYInset() {
        return leftStackYInset;
    }

    public double getRightStackXInset() {
        return rightStackXInset;
    }

    public double getRightStackYInset() {
        return rightStackYInset;
    }
}
