package behaviour.shapes.util;

import logs.LoggingManager;
import model.shapes.Shape;

import javax.naming.SizeLimitExceededException;
import java.util.LinkedList;
import java.util.Queue;

public class ShapePool {
    private static final int MAX_SIZE = 100;
    private static ShapePool shapePool = null;
    private Queue<Shape> reusableShapes = null;

    public synchronized static ShapePool getInstance() {
        if (shapePool == null) {
            shapePool = new ShapePool();
        }
        return shapePool;
    }
    private ShapePool() {
        reusableShapes = new LinkedList<>();
    }

    public Shape create() {
        if (!isEmpty()) {
            LoggingManager.getInstance().info( "SHAPE REUSED FROM SHAPE POOL");
            return reusableShapes.poll();
        }
        Shape shape = ShapeCreator.createShape();
        shape.setState(Shape.State.CREATED);
        LoggingManager.getInstance().info( "NEW SHAPE CREATED");
        return shape;
    }

    public synchronized void addReusableShape(final Shape shape) throws
            SizeLimitExceededException {
        if (isFull()) {
            LoggingManager.getInstance().error("SHAPE POOL IS FULL !");
            throw new SizeLimitExceededException();
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
