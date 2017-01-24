package behaviour.shapes;

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
		context.getShape().setState(Shape.State.FETCHED);
		if (player.getCharacter().getLeftStack().size() >= 3) {
			if (player.getCharacter().getLeftStack().peek().equals(context.getShape())) {
				final Shape shape1 = player.getCharacter().popFromLeftStack();
				final Shape shape2 = player.getCharacter().popFromLeftStack();
				final Shape shape3 = player.getCharacter().popFromLeftStack();
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
					player.getCharacter().addToLeftStack(shape3);
					player.getCharacter().addToLeftStack(shape2);
					player.getCharacter().addToLeftStack(shape1);
				}
			}
		}
		if (player.getCharacter().getRightStack().size() >= 3) {
			if (player.getCharacter().getRightStack().peek().equals(context.getShape())) {
				final Shape shape1 = player.getCharacter().popFromRightStack();
				final Shape shape2 = player.getCharacter().popFromRightStack();
				final Shape shape3 = player.getCharacter().popFromRightStack();
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
					player.getCharacter().addToRightStack(shape3);
					player.getCharacter().addToRightStack(shape2);
					player.getCharacter().addToRightStack(shape1);
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
