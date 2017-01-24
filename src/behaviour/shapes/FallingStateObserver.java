package behaviour.shapes;

import javafx.scene.layout.AnchorPane;
import model.Player;
import model.shapes.Shape;
import util.Point;

import java.util.Observable;
import java.util.Observer;

public class FallingStateObserver implements Observer {

    public FallingStateObserver() {
    }

    @Override
    public void update(final Observable fallingState, final Object shape) {
        Point point = new Point(((Shape) shape).getImageView().getX(),
                ((Shape) shape).getImageView().getY());
        for (Player player : ((FallingState) fallingState).getContext()
                .getPlayersController().getPlayers()) {
            Point leftStack = new Point(player.getCharacter().getX() + player
                    .getLeftStackXInset(), player.getCharacter().getY() - player
                    .getLeftStackYInset());
            Point rightStack = new Point(player.getCharacter().getX() + player
                    .getRightStackXInset(), player.getCharacter().getY() - player
                    .getRightStackYInset());
            if (Math.abs(leftStack.getX() - point.getX()) <= ((Shape) shape).getImageView()
                    .getImage().getWidth() / 4
                    && Math.abs(leftStack.getY() - point.getY()) <= 5){
                ((Shape) shape).setX(player.getCharacter().getImageView().getX());
                ((Shape) shape).getImageView().translateXProperty().bind(player.getCharacter()
                        .getImageView().translateXProperty());
                ((Shape) shape).setY(player.getCharacter().getY() - player.getLeftStackYInset());
                ((Shape) shape).getImageView().translateYProperty().bind(player.getCharacter()
                        .getImageView().translateYProperty());
                player.addToLeftStack((Shape) shape);
                ((FallingState) fallingState).getContext().getShape().setState(
                        Shape.State.FETCHED);
                ((FallingState) fallingState).goNext(player);
            } else if (Math.abs(rightStack.getX() - point.getX()) <= ((Shape) shape).getImageView()
                    .getImage().getWidth() / 4
                    && Math.abs(rightStack.getY() - point.getY()) <= 5) {
                ((Shape) shape).setX(player.getCharacter().getImageView().getX() + player
                        .getRightStackXInset() - player.getLeftStackXInset());
                ((Shape) shape).getImageView().translateXProperty().bind(player.getCharacter()
                        .getImageView().translateXProperty());
                ((Shape) shape).setY(player.getCharacter().getY() - player.getRightStackYInset());
                ((Shape) shape).getImageView().translateYProperty().bind(player.getCharacter()
                        .getImageView().translateYProperty());
                //TODO: set a bottom anchor for shape.
                player.addToRightStack((Shape) shape);
                ((FallingState) fallingState).getContext().getShape().setState(
                        Shape.State.FETCHED);
                ((FallingState) fallingState).goNext(player);
            }
        }
    }
}
