package behaviour.shapes;

import java.util.Observable;

import controller.MainController;
import model.Player;
import model.shapes.Shape;
import util.Score;

class FetchedState extends Observable implements ShapeState {

	private MainController mainController = null;
	private Player player = null;
	private ShapeContext context = null;

	protected FetchedState(final MainController mainController, final Player player,
						   final ShapeContext context) {
		this.mainController = mainController;
		this.player = player;
		this.context = context;
	}

    @Override
    public final void handle(final Shape shape) {
		//TODO: Replace with player in field.

    	//System.out.println("Shape FETCHED");
    	for (final Player player : this.mainController.getPlayersController().getPlayers()) {
    		if (player.getLeftStack().size() >= 3) {
    			if (player.getLeftStack().peek().equals(shape)) {
    				//System.out.println("Found shape in player's left Stack.");
    				final Shape shape1 = player.getLeftStack().pop();
    				final Shape shape2 = player.getLeftStack().pop();
    				final Shape shape3 = player.getLeftStack().pop();
    				if (shape1.getColor().
    						equals(shape2.getColor())
    						&& shape1.getColor().equals(shape3.getColor())) {
    					//System.out.println("Removing shapes from left stack");
    					final Score currentScore = player.getScore();
    					currentScore.setPoints(currentScore.getPoints() + 1);
    					player.setScore(currentScore);
    					System.out.println("Shapes colors: " + shape1.getColor()
    					+ " " + shape2.getColor() + " " + shape3.getColor());
    					System.out.println(player.getScore().getPoints());
//    					this.mainController.getGameView().
//    					getRootPane().getChildren().remove(shape1.getImageView());
//    					this.mainController.getGameView().
//    					getRootPane().getChildren().remove(shape2.getImageView());
//    					this.mainController.getGameView().
//    					getRootPane().getChildren().remove(shape3.getImageView());
    					notifyObservers();
    				} else {
    					player.getLeftStack().push(shape1);
    					player.getLeftStack().push(shape2);
    					player.getLeftStack().push(shape3);
    				}
    				break;
    			}
    		}
    		if (player.getRightStack().size() >= 3) {
    			if (player.getRightStack().peek().equals(shape)) {
    				//System.out.println("Found shape in player's right Stack.");
    				final Shape shape1 = player.getRightStack().pop();
    				final Shape shape2 = player.getRightStack().pop();
    				final Shape shape3 = player.getRightStack().pop();
    				if (shape1.getColor().
    						equals(shape2.getColor())
    						&& shape1.getColor().equals(shape3.getColor())) {
    					//System.out.println("Removing shapes from right stack");
    					System.out.println("Shapes colors: " + shape1.getColor()
    					+ " " + shape2.getColor() + " " + shape3.getColor());
    					final Score currentScore = player.getScore();
    					currentScore.setPoints(currentScore.getPoints() + 1);
    					player.setScore(currentScore);
    					System.out.println(player.getScore().getPoints());
//    					this.mainController.getGameView().
//    					getRootPane().getChildren().remove(shape1.getImageView());
//    					this.mainController.getGameView().
//    					getRootPane().getChildren().remove(shape2.getImageView());
//    					this.mainController.getGameView().
//    					getRootPane().getChildren().remove(shape3.getImageView());
    					notifyObservers();
    				} else {
    					player.getRightStack().push(shape1);
    					player.getRightStack().push(shape2);
    					player.getRightStack().push(shape3);
    				}
    				break;
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
