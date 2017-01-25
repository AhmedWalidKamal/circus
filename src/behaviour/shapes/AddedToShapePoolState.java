package behaviour.shapes;

import javafx.application.Platform;
import model.shapes.Shape;

class AddedToShapePoolState implements ShapeState {

	private ShapeContext context;

	public AddedToShapePoolState(final ShapeContext context) {
		this.context = context;
	}
	@Override
	public void handle() {
		Platform.runLater(() -> {
			context.getShape().setState(Shape.State.IN_POOL);
			context.getShapePool().addReusableShape(context.getShape());
			context.getViewController().removeFromRootPane(context.getShapeImageView());
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
