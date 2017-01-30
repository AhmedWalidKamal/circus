package behaviour.shapes;

import behaviour.shapes.util.ShapePool;
import javafx.application.Platform;
import logs.LoggingManager;
import model.shapes.Shape;

import javax.naming.SizeLimitExceededException;

class AddedToShapePoolState implements ShapeState {

	private ShapeContext context;

	public AddedToShapePoolState(final ShapeContext context) {
		this.context = context;
	}
	@Override
	public void handle() {
		Platform.runLater(() -> {
			context.getShape().setState(Shape.State.IN_POOL);
			try {
				ShapePool.getInstance().addReusableShape(context.getShape());
			} catch (SizeLimitExceededException e) {
				e.printStackTrace();
			}
			context.getViewController().removeFromRootPane(context.getShapeImageView());
			LoggingManager.getInstance().info("FALLEN SHAPE ADDED BACK TO THE SHAPE POOL");
        });
	}
    @Override
    public ShapeContext getContext() {
        return this.context;
    }

    @Override
    public void setContext(final ShapeContext context) {
    	this.context = context;
    }
}
