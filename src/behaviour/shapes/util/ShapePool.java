package behaviour.shapes.util;

import model.shapes.Shape;

import java.util.LinkedList;
import java.util.Queue;

public class ShapePool {
    private static final int MAX_SIZE = 100;
    private Queue<Shape> reusableShapes = null;

    public ShapePool() {
        reusableShapes = new LinkedList<>();
    }

    public Shape create() {
        if (!isEmpty()) {
            return reusableShapes.poll();
        }
        Shape shape = ShapeCreator.createShape();
        shape.setState(Shape.State.CREATED);
        return shape;
    }

    public synchronized void addReusableShape(final Shape shape) {
        if (isFull()) {
            throw new RuntimeException("Pool is full!");
        }
        reusableShapes.add(shape);
    }

    public synchronized void releaseShape(Shape shape) {
        shape = null;
    }

    public boolean isEmpty() {
        return reusableShapes.isEmpty();
    }

    public boolean isFull() {
        return reusableShapes.size() == MAX_SIZE;
    }
}
