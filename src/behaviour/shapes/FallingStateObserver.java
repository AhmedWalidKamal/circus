package behaviour.shapes;

import javafx.scene.layout.AnchorPane;
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
    public void update(final Observable fallingState, final Object shape) {
        FallingState state = (FallingState) fallingState;
        Shape observedShape = (Shape) shape;
        Point position = new Point(observedShape.getImageView().getX(),
                observedShape.getImageView().getY());
        for (Player player : state.getContext().getPlayersController().getPlayers()) {
            Character character = player.getCharacter();
            Point leftStack = new Point(character.getX() + character.getTranslateX()
                    + character.getLeftStackXInset(), character.getY() + character
                    .getTranslateY() - character.getLeftStackYInset());
            Point rightStack = new Point(character.getX() + character.getTranslateX()
                    + character.getRightStackXInset(), character.getY() + character
                    .getTranslateY() - character.getRightStackYInset());
            if (Math.abs(leftStack.getX() - position.getX()) <= observedShape.getImageView()
                    .getImage().getWidth() / 4 && Math.abs(leftStack.getY()
                    - position.getY()) <= 5) {
                observedShape.setX(character.getX());
                observedShape.getImageView().translateXProperty().bind(character
                        .getTranslateXProperty());
                observedShape.setY(character.getY() - character.getLeftStackYInset());
                observedShape.getImageView().translateYProperty().bind(character
                        .getTranslateYProperty());
                character.addToLeftStack(observedShape);
                state.getContext().getShape().setState(Shape.State.FETCHED);
                state.goNext(player);
            } else if (Math.abs(rightStack.getX() - position.getX()) <= observedShape
                    .getImageView().getImage().getWidth() / 4 && Math.abs(rightStack
                    .getY() - position.getY()) <= 5) {
                observedShape.setX(character.getX() + character.getRightStackXInset()
                        - character.getLeftStackXInset());
                observedShape.getImageView().translateXProperty().bind(character
                        .getTranslateXProperty());
                observedShape.setY(character.getY() - character.getRightStackYInset());
                observedShape.getImageView().translateYProperty().bind(character
                        .getTranslateYProperty());
                character.addToRightStack(observedShape);
                state.getContext().getShape().setState(Shape.State.FETCHED);
                state.goNext(player);
            }
        }
    }
}
