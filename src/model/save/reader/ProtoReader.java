package model.save.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.Color;
import model.Player;
import model.Timer;
import model.characters.Character;
import model.characters.util.CharacterFactory;
import model.save.ModelMemento;
import model.save.util.protobuff.ProtoBuffGame.ProtoGame;
import model.save.util.protobuff.ProtoBuffGame.ProtoGame.ProtoPlayer;
import model.save.util.protobuff.ProtoBuffGame.ProtoGame.ProtoPlayer.ProtoCharacter.ProtoShape;
import model.shapes.Shape;
import model.shapes.Shape.State;
import model.shapes.util.ShapeFactory;
import util.Score;

public class ProtoReader implements Reader {

    /**
     * Instantiates a new protocol buffer reader.
     */
    public ProtoReader() {

    }

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

	@Override
	public ModelMemento loadMemento(final String path)
			throws IOException {
		final File protoBuffFile = new File(path);
        ProtoGame protoGame = ProtoGame.
		        parseFrom(new FileInputStream(protoBuffFile));
        return createModelMemento(protoGame);
	}

	private ModelMemento createModelMemento(final ProtoGame protoGame) {
    	loadDefaultClasses();
		ModelMemento loadedMemento = new ModelMemento();
		setMementoAttributes(loadedMemento, protoGame);
		List<Player> players = new ArrayList<>();
		for (ProtoPlayer protoPlayer : protoGame.getPlayersList()) {
			Player player = new Player();
			setPlayerAttributes(player, protoPlayer);
			players.add(player);
		}
		loadedMemento.setPlayers(players);
		return loadedMemento;
	}

	private void loadDefaultClasses() {
		try {
			Class.forName("model.shapes.Lego");
			Class.forName("model.shapes.Plate");
			Class.forName("model.characters.supported_characters.GreenClown");
			Class.forName("model.characters.supported_characters.RedClown");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void setMementoAttributes(final ModelMemento loadedMemento, final ProtoGame protoGame) {
		loadedMemento.setTimer(new Timer(protoGame.getCurrentTime()));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		loadedMemento.setLocalDateTime(LocalDateTime.parse(protoGame.getLocalDateTime(), formatter));
		loadedMemento.setDifficultyLevel(protoGame.getLevelDifficulty());
	}

	private void setPlayerAttributes(final Player player, final ProtoPlayer protoPlayer) {
		player.setName(protoPlayer.getPlayerName());
		player.setScore(new Score(protoPlayer.getPlayerScore()));
		Character character
		= CharacterFactory.getInstance().
		createCharacter(protoPlayer.
				getPlayerCharacter().getKey());
		setCharacterAttributes(character, protoPlayer);
		player.setCharacter(character);
	}

	private void setCharacterAttributes(final Character character, final ProtoPlayer protoPlayer) {
		character.setHeight(protoPlayer.getPlayerCharacter().getHeight());
		character.setWidth(protoPlayer.getPlayerCharacter().getWidth());
		character.setLeftStackXInset(protoPlayer.getPlayerCharacter().getLeftStackXInset());
		character.setLeftStackYInset(protoPlayer.getPlayerCharacter().getLeftStackYInset());
		character.setRightStackXInset(protoPlayer.getPlayerCharacter().getRightStackXInset());
		character.setRightStackYInset(protoPlayer.getPlayerCharacter().getRightStackYInset());
		character.setxProperty(protoPlayer.getPlayerCharacter().getXProperty());
		character.setyProperty(protoPlayer.getPlayerCharacter().getYProperty());
		character.setTranslateXProperty(protoPlayer.getPlayerCharacter().getTranslateXProperty());
		character.setTranslateYProperty(protoPlayer.getPlayerCharacter().getTranslateYProperty());
		character.setUrl(protoPlayer.getPlayerCharacter().getUrl());
		character.setKey(protoPlayer.getPlayerCharacter().getKey());
		Stack<Shape> leftStack = new Stack<>();
		Stack<Shape> rightStack = new Stack<>();
		for (ProtoShape protoShape : protoPlayer.getPlayerCharacter().getLeftStackList()) {
			Shape shape = ShapeFactory.getInstance().
					createShape(protoShape.getKey(), getColor(protoShape.getColor()));
			setShapeAttributes(shape, protoShape);
			leftStack.push(shape);
		}
		for (ProtoShape protoShape : protoPlayer.getPlayerCharacter().getRightStackList()) {
			Shape shape = ShapeFactory.getInstance().
					createShape(protoShape.getKey(), getColor(protoShape.getColor()));
			setShapeAttributes(shape, protoShape);
			rightStack.push(shape);
		}
		character.setLeftStack(leftStack);
		character.setRightStack(rightStack);
	}

	private void setShapeAttributes(final Shape shape, final ProtoShape protoShape) {
		shape.setColor(getColor(protoShape.getColor()));
		shape.setHeight(protoShape.getHeight());
		shape.setkey(protoShape.getKey());
		shape.setState(getState(protoShape.getState()));
		shape.setUrl(protoShape.getUrl());
		shape.setWidth(protoShape.getWidth());
		shape.setX(protoShape.getX());
		shape.setY(protoShape.getY());
		shape.setTranslateXProperty(protoShape.getTranslateX());
		shape.setTranslateYProperty(protoShape.getTranslateY());
	}

	private State getState(final String state) {
		for (State enumState : State.values()) {
			if (enumState.toString().equalsIgnoreCase(state)) {
				return enumState;
			}
		}
		return null;
	}

	private Color getColor(final String color) {
		for (Color enumColor : Color.values()) {
			if (enumColor.toString().equalsIgnoreCase(color)) {
				return enumColor;
			}
		}
		return null;
	}
}
