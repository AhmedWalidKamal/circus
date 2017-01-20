package model;

import java.util.Stack;

import model.characters.Character;
import model.shapes.Shape;
import util.Score;

public class Player implements Comparable<Player> {

    private Score score;
    private String name;
    private Character character;
    private Stack<Shape> leftStack = null;
    private Stack<Shape> rightStack = null;

    //TODO: Separate this stuff in Character class.
    private final double leftStackXInset = 50;
    private final double rightStackXInset = 175;
    private double leftStackYInset = 0;
    private double rightStackYInset = 0;

    public Player() {
        score = new Score();
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    public void setPlayerName(final String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return this.name;
    }

    public void setScore(final Score score) {
        this.score = score;
    }

    public Score getScore() {
        return this.score;
    }

    public Character getCharacter() {
        return this.character;
    }

    public void setCharacter(final Character character) {
        this.character = character;
    }

    @Override
    public int compareTo(final Player player) {
        if (score.compareTo(player.getScore()) != 0) {
            return score.compareTo(player.getScore());
        } else {
            return name.compareTo(player.getPlayerName());
        }
    }

    public void addToLeftStack(final Shape shape) {
        leftStack.add(shape);
        leftStackYInset += shape.getImageView().getImage().getHeight();
    }

    public void addToRightStack(final Shape shape) {
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

    public Stack<Shape> getLeftStack() {
    	return this.leftStack;
    }
    public Stack<Shape> getRightStack() {
    	return this.rightStack;
    }
}
