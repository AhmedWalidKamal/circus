package model.characters;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.shapes.Shape;

import java.util.Stack;

public abstract class Character {
    protected Stack<Shape> leftStack = null;
    protected Stack<Shape> rightStack = null;
    protected double height = 0;
    protected double width = 0;
    protected double leftStackYInset = 0;
    protected DoubleProperty xProperty = null;
    protected DoubleProperty yProperty = null;
    protected DoubleProperty translateXProperty = null;
    protected DoubleProperty translateYProperty = null;
    protected double rightStackYInset = 0;
    protected double leftStackXInset = 0;
    protected double rightStackXInset = 0;

    public Character() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
        xProperty = new SimpleDoubleProperty(0);
        yProperty = new SimpleDoubleProperty(0);
        translateXProperty = new SimpleDoubleProperty(0);
        translateYProperty = new SimpleDoubleProperty(0);
    }

    public double getX() {
        return xProperty.doubleValue();
    }

    public void setX(final double x) {
        xProperty.set(x);
    }

    public double getY() {
        return yProperty.doubleValue();
    }

    public void setY(final double y) {
        yProperty.set(y);
    }

    public void bindX(final DoubleProperty x) {
        xProperty.bind(x);
    }

    public void bindY(final DoubleProperty y) {
        yProperty.bind(y);
    }

    public double getTranslateX() {
        return translateXProperty.doubleValue();
    }

    public void setTranslateX(double translateX) {
        translateXProperty.set(translateX);
    }

    public double getTranslateY() {
        return translateYProperty.doubleValue();
    }

    public void setTranslateY(double translateY) {
        translateYProperty.set(translateY);
    }

    public void bindTranslateX(DoubleProperty translateX) {
        translateXProperty.bind(translateX);
    }

    public void bindTranslateY(DoubleProperty translateY) {
        translateYProperty.bind(translateY);
    }

    public DoubleProperty getXProperty() {
        return xProperty;
    }

    public DoubleProperty getYProperty() {
        return yProperty;
    }

    public DoubleProperty getTranslateXProperty() {
        return translateXProperty;
    }

    public DoubleProperty getTranslateYProperty() {
        return translateYProperty;
    }

    public void addToLeftStack(final Shape shape) {
        leftStack.add(shape);
        leftStackYInset += shape.getImageView().getImage().getHeight();
    }

    public void addToRightStack(final Shape shape) {
        rightStack.add(shape);
        rightStackYInset += shape.getImageView().getImage().getHeight();
    }

    public Shape popFromLeftStack() {
        if (leftStack.isEmpty()) {
            return null;
        }
        leftStackYInset -= leftStack.peek().getImageView().getImage().getHeight();
        return leftStack.pop();
    }

    public Shape popFromRightStack() {
        if (rightStack.isEmpty()) {
            return null;
        }
        rightStackYInset -= rightStack.peek().getImageView().getImage().getHeight();
        return rightStack.pop();
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

    public Stack<Shape> getLeftStack() {
        return this.leftStack;
    }

    public Stack<Shape> getRightStack() {
        return this.rightStack;
    }

    public abstract String getUrl();

    public abstract String getKey();
}
