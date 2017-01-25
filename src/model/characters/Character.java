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
    protected double leftStackXInset = 0;
    protected double leftStackYInset = 0;
    protected double rightStackXInset = 0;
    protected double rightStackYInset = 0;
    protected DoubleProperty xProperty = null;
    protected DoubleProperty yProperty = null;
    protected DoubleProperty translateXProperty = null;
    protected DoubleProperty translateYProperty = null;
    protected String url = null;
    protected String key = null;

    public Character() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
        xProperty = new SimpleDoubleProperty(0);
        yProperty = new SimpleDoubleProperty(0);
        translateXProperty = new SimpleDoubleProperty(0);
        translateYProperty = new SimpleDoubleProperty(0);
        url = "";
        key = "";
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

    public DoubleProperty getxProperty() {
        return xProperty;
    }

    public void setxProperty(final double xProperty) {
        this.xProperty.set(xProperty);
    }

    public DoubleProperty getyProperty() {
        return yProperty;
    }

    public void setyProperty(final double yProperty) {
        this.yProperty.set(yProperty);
    }

    public DoubleProperty getTranslateXProperty() {
        return translateXProperty;
    }

    public void setTranslateXProperty(final double translateXProperty) {
        this.translateXProperty.set(translateXProperty);
    }

    public DoubleProperty getTranslateYProperty() {
        return translateYProperty;
    }

    public void setTranslateYProperty(final double translateYProperty) {
        this.translateYProperty.set(translateYProperty);
    }

    public void addToLeftStack(final Shape shape) {
        leftStack.add(shape);
        leftStackYInset += shape.getHeight();
    }

    public void addToRightStack(final Shape shape) {
        rightStack.add(shape);
        rightStackYInset += shape.getHeight();
    }

    public Shape popFromLeftStack() {
        if (leftStack.isEmpty()) {
            return null;
        }
        leftStackYInset -= leftStack.peek().getHeight();
        return leftStack.pop();
    }

    public Shape popFromRightStack() {
        if (rightStack.isEmpty()) {
            return null;
        }
        rightStackYInset -= rightStack.peek().getHeight();
        return rightStack.pop();
    }

    public double getLeftStackXInset() {
        return leftStackXInset;
    }

    public void setLeftStackXInset(final double leftStackXInset) {
        this.leftStackXInset = leftStackXInset;
    }

    public double getLeftStackYInset() {
        return leftStackYInset;
    }

    public void setLeftStackYInset(final double leftStackYInset) {
        this.leftStackYInset = leftStackYInset;
    }

    public double getRightStackXInset() {
        return rightStackXInset;
    }

    public void setRightStackXInset(final double rightStackXInset) {
        this.rightStackXInset = rightStackXInset;
    }

    public double getRightStackYInset() {
        return rightStackYInset;
    }

    public void setRightStackYInset(final double rightStackYInset) {
        this.rightStackYInset = rightStackYInset;
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

    public void setLeftStack(final Stack<Shape> leftStack) {
        this.leftStack = leftStack;
    }

    public Stack<Shape> getRightStack() {
        return this.rightStack;
    }

    public void setRightStack(final Stack<Shape> rightStack) {
        this.rightStack = rightStack;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }
}
