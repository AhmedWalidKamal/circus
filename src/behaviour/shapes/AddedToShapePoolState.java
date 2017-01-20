package behaviour.shapes;

import controller.MainController;
import javafx.application.Platform;
import model.shapes.Shape;

class AddedToShapePoolState implements ShapeState {

	private final MainController mainController;
	private ShapeContext context;

	public AddedToShapePoolState(final MainController mainController, final ShapeContext context) {
		this.mainController = mainController;
		this.context = context;
	}
	@Override
	public void handle(final Shape shape) {
		Platform.runLater(() -> {
			this.context.getShapePool().addReusableShape(shape);
			this.mainController.getGameView().
			getRootPane().getChildren().remove(shape.getImageView());
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
