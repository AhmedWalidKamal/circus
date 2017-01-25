package model.save.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import model.Player;
import model.save.ModelMemento;
import model.save.util.ProtoBuffGame.ProtoGame;
import model.save.util.ProtoBuffGame.ProtoGame.ProtoPlayer;
import model.save.util.ProtoBuffGame.ProtoGame.ProtoPlayer.ProtoCharacter;
import model.save.util.ProtoBuffGame.ProtoGame.ProtoPlayer.ProtoCharacter.ProtoShape;
import model.shapes.Shape;

public class ProtoWriter implements Writer {

    /**
     * The Protocol Buffer Extenstion.
     */
    private static final String PROTOCOL_BUFFER_EXTENSION
            = ".protobuff";

	public ProtoWriter() {

	}

	@Override
	public void saveMemento(final ModelMemento memento) throws IOException {
		final ProtoGame protoGame = createProtoGame(memento);
        final File protoBuffFile = new File("." + File.separator + PROTOCOL_BUFFER_EXTENSION);
        if (!protoBuffFile.exists()) {
            protoBuffFile.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(protoBuffFile);
        protoGame.writeTo(outputStream);
        outputStream.close();
	}

	private ProtoGame createProtoGame(final ModelMemento memento) {
		ProtoGame.Builder protoGameBuilder = ProtoGame.newBuilder();
		protoGameBuilder.setCurrentTime(memento.getTimer().getCurrentTime());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		protoGameBuilder.setLocalDateTime(memento.getLocalDateTime().format(formatter));
		for (Player player : memento.getPlayers()) {
			ProtoPlayer.Builder protoPlayerBuilder = ProtoPlayer.newBuilder();
			protoPlayerBuilder.setPlayerName(player.getName());
			protoPlayerBuilder.setPlayerScore(player.getScore().getCurrentScore());
			ProtoCharacter.Builder protoCharBuilder = ProtoCharacter.newBuilder();
			protoCharBuilder.setWidth(player.getCharacter().getWidth());
			protoCharBuilder.setHeight(player.getCharacter().getHeight());
			protoCharBuilder.setLeftStackXInset(player.getCharacter().getLeftStackXInset());
			protoCharBuilder.setLeftStackYInset(player.getCharacter().getLeftStackYInset());
			protoCharBuilder.setRightStackXInset(player.getCharacter().getRightStackXInset());
			protoCharBuilder.setRightStackYInset(player.getCharacter().getRightStackYInset());
			protoCharBuilder.setXProperty(player.getCharacter().getxProperty().doubleValue());
			protoCharBuilder.setYProperty(player.getCharacter().getyProperty().doubleValue());
			protoCharBuilder.setTranslateXProperty(player.getCharacter().getTranslateXProperty().doubleValue());
			protoCharBuilder.setTranslateYProperty(player.getCharacter().getTranslateYProperty().doubleValue());
			protoCharBuilder.setUrl(player.getCharacter().getUrl());
			protoCharBuilder.setKey(player.getCharacter().getKey());
			for (Shape shape : player.getCharacter().getLeftStack()) {
				ProtoShape.Builder protoShapeBuilder = ProtoShape.newBuilder();
				protoShapeBuilder.setState(shape.getState().toString());
				protoShapeBuilder.setColor(shape.getColor().toString());
				protoShapeBuilder.setUrl(shape.getUrl());
				protoShapeBuilder.setWidth(shape.getWidth());
				protoShapeBuilder.setHeight(shape.getHeight());
				protoShapeBuilder.setX(shape.getX());
				protoShapeBuilder.setY(shape.getY());
				protoShapeBuilder.setTranslateX(shape.getTranslateX().doubleValue());
				protoShapeBuilder.setTranslateY(shape.getTranslateY().doubleValue());
				protoShapeBuilder.setKey(shape.getKey());
				protoCharBuilder.addLeftStack(protoShapeBuilder);
			}
			for (Shape shape : player.getCharacter().getRightStack()) {
				ProtoShape.Builder protoShapeBuilder = ProtoShape.newBuilder();
				protoShapeBuilder.setState(shape.getState().toString());
				protoShapeBuilder.setColor(shape.getColor().toString());
				protoShapeBuilder.setUrl(shape.getUrl());
				protoShapeBuilder.setWidth(shape.getWidth());
				protoShapeBuilder.setHeight(shape.getHeight());
				protoShapeBuilder.setX(shape.getX());
				protoShapeBuilder.setY(shape.getY());
				protoShapeBuilder.setTranslateX(shape.getTranslateX().doubleValue());
				protoShapeBuilder.setTranslateY(shape.getTranslateY().doubleValue());
				protoShapeBuilder.setKey(shape.getKey());
				protoCharBuilder.addRightStack(protoShapeBuilder);
			}
			protoPlayerBuilder.setPlayerCharacter(protoCharBuilder);
			protoGameBuilder.addPlayers(protoPlayerBuilder);
		}
		return protoGameBuilder.build();
	}

}
