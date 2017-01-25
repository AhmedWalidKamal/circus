package behaviour.shapes;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import model.Player;
import model.characters.Character;
import model.shapes.Shape;
import util.Point;

import java.util.Observable;
import java.util.Observer;

public class FallingStateObserver implements Observer {

    public FallingStateObserver() {

    }

    @Override
    public void update(final Observable fallingState, final Object shapeImageViewPair) {
        FallingState state = (FallingState) fallingState;
        Shape shape = ((Pair<Shape, ImageView>) shapeImageViewPair).getKey();
        ImageView shapeImageView = ((Pair<Shape, ImageView>) shapeImageViewPair).getValue();
        Point position = new Point(shapeImageView.getX(),
                shapeImageView.getY());
        for (Player player : state.getContext().getPlayersController().getPlayers()) {
            Character character = player.getCharacter();
            Point leftStack = new Point(character.getX() + character.getTranslateX()
                    + character.getLeftStackXInset(), character.getY() + character
                    .getTranslateY() - character.getLeftStackYInset());
            Point rightStack = new Point(character.getX() + character.getTranslateX()
                    + character.getRightStackXInset(), character.getY() + character
                    .getTranslateY() - character.getRightStackYInset());
            if (Math.abs(leftStack.getX() - position.getX()) <= shapeImageView
                    .getImage().getWidth() / 4 && Math.abs(leftStack.getY()
                    - position.getY()) <= 5) {
                shapeImageView.setX(character.getX());
                shapeImageView.translateXProperty().bind(character
                        .getTranslateXProperty());
                shapeImageView.setY(character.getY() - character.getLeftStackYInset());
                shapeImageView.translateYProperty().bind(character
                        .getTranslateYProperty());
                character.addToLeftStack(shape);
                state.getContext().getShape().setState(Shape.State.FETCHED);
                state.goNext(player);
            } else if (Math.abs(rightStack.getX() - position.getX()) <= shapeImageView
                    .getImage().getWidth() / 4 && Math.abs(rightStack
                    .getY() - position.getY()) <= 5) {
                shapeImageView.setX(character.getX() + character.getRightStackXInset()
                        - character.getLeftStackXInset());
                shapeImageView.translateXProperty().bind(character
                        .getTranslateXProperty());
                shapeImageView.setY(character.getY() - character.getRightStackYInset());
                shapeImageView.translateYProperty().bind(character
                        .getTranslateYProperty());
                character.addToRightStack(shape);
                state.getContext().getShape().setState(Shape.State.FETCHED);
                state.goNext(player);
            }
        }
    }
}
