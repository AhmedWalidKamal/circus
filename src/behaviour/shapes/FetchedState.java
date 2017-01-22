package behaviour.shapes;

import controller.MainController;
import javafx.application.Platform;
import model.Player;
import model.shapes.Shape;

class FetchedState implements ShapeState {

	private Player player = null;
	private ShapeContext context = null;

	protected FetchedState(final ShapeContext context, final Player player) {
		this.player = player;
		this.context = context;
	}

	@Override
	public final void handle() {
		if (player.getLeftStack().size() >= 3) {
			if (player.getLeftStack().peek().equals(context.getShape())) {
				final Shape shape1 = player.popFromLeftStack();
				final Shape shape2 = player.popFromLeftStack();
				final Shape shape3 = player.popFromLeftStack();
				if (shape1.getColor().equals(shape2.getColor())
						&& shape1.getColor().
						equals(shape3.getColor())) {
					context.getGameUtilController().updateScore(player);
					Platform.runLater(() -> {
						context.getViewController().removeFromRootPane(shape1.getImageView());
						context.getViewController().removeFromRootPane(shape2.getImageView());
						context.getViewController().removeFromRootPane(shape3.getImageView());
					});
				} else {
					player.addToLeftStack(shape3);
					player.addToLeftStack(shape2);
					player.addToLeftStack(shape1);
				}
			}
		}
		if (player.getRightStack().size() >= 3) {
			if (player.getRightStack().peek().equals(context.getShape())) {
				final Shape shape1 = player.popFromRightStack();
				final Shape shape2 = player.popFromRightStack();
				final Shape shape3 = player.popFromRightStack();
				if (shape1.getColor().
						equals(shape2.getColor())
						&& shape1.getColor().equals(shape3.getColor())) {
					context.getGameUtilController().updateScore(player);
					Platform.runLater(() -> {
						context.getViewController().removeFromRootPane(shape1.getImageView());
						context.getViewController().removeFromRootPane(shape2.getImageView());
						context.getViewController().removeFromRootPane(shape3.getImageView());

					});
				} else {
					player.addToRightStack(shape3);
					player.addToRightStack(shape2);
					player.addToRightStack(shape1);
				}
			}
		}
	}

	@Override
	public ShapeContext getContext() {
		return context;
	}

	@Override
	public void setContext(final ShapeContext context) {
		this.context = context;
	}
}
