package behaviour.shapes;

import java.util.Observable;

import controller.MainController;
import javafx.application.Platform;
import model.Player;
import model.shapes.Shape;

class FetchedState extends Observable implements ShapeState {

	private MainController mainController = null;
	private Player player = null;
	private ShapeContext context = null;

	protected FetchedState(final MainController mainController,
			final Player player, final ShapeContext context) {
		this.mainController = mainController;
		this.player = player;
		this.context = context;
	}

    @Override
    public final void handle(final Shape shape) {
    	if (player.getLeftStack().size() >= 3) {
    		if (player.getLeftStack().peek().equals(shape)) {
    			final Shape shape1 = player.popFromLeftStack();
    			final Shape shape2 = player.popFromLeftStack();
    			final Shape shape3 = player.popFromLeftStack();
    			if (shape1.getColor().equals(shape2.getColor())
    						&& shape1.getColor().
    						equals(shape3.getColor())) {
    				this.mainController.getGameUtilController().updateScore(player);
    				Platform.runLater(() -> {
						this.mainController.getGameView().
								getRootPane().getChildren().remove(shape1.getImageView());
						this.mainController.getGameView().
								getRootPane().getChildren().remove(shape2.getImageView());
						this.mainController.getGameView().
								getRootPane().getChildren().remove(shape3.getImageView());
						notifyObservers();
					});
    			} else {
					player.addToLeftStack(shape3);
					player.addToLeftStack(shape2);
					player.addToLeftStack(shape1);
    			}
    		}
    	}
    	if (player.getRightStack().size() >= 3) {
    		if (player.getRightStack().peek().equals(shape)) {
    			final Shape shape1 = player.popFromRightStack();
    			final Shape shape2 = player.popFromRightStack();
    			final Shape shape3 = player.popFromRightStack();
    			if (shape1.getColor().
    					equals(shape2.getColor())
    					&& shape1.getColor().equals(shape3.getColor())) {
    				this.mainController.getGameUtilController().updateScore(player);
    				Platform.runLater(() -> {
						this.mainController.getGameView().
								getRootPane().getChildren().remove(shape1.getImageView());
						this.mainController.getGameView().
								getRootPane().getChildren().remove(shape2.getImageView());
						this.mainController.getGameView().
								getRootPane().getChildren().remove(shape3.getImageView());
						notifyObservers();
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
	public void setContext(final ShapeContext context) {
		this.context = context;
	}

	// TODO: Use this to update the view.
    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}
