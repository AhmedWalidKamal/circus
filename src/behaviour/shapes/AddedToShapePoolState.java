package behaviour.shapes;

import javafx.application.Platform;

class AddedToShapePoolState implements ShapeState {

	private ShapeContext context;

	public AddedToShapePoolState(final ShapeContext context) {
		this.context = context;
	}
	@Override
	public void handle() {
		Platform.runLater(() -> {
			context.getShapePool().addReusableShape(context.getShape());
			context.getViewController().removeFromRootPane(context.getShape()
					.getImageView());
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
