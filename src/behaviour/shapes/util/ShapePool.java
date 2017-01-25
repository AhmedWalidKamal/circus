package behaviour.shapes.util;

import logs.LogsManager;
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
            LogsManager.getInstance().info( "SHAPE REUSED FROM SHAPE POOL");
            return reusableShapes.poll();
        }
        Shape shape = ShapeCreator.createShape();
        shape.setState(Shape.State.CREATED);
        LogsManager.getInstance().info( "NEW SHAPE CREATED");
        return shape;
    }

    public synchronized void addReusableShape(final Shape shape) {
        if (isFull()) {
            LogsManager.getInstance().error("SHAPE POOL IS FULL !");
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
