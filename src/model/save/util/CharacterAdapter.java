package model.save.util;

import com.google.gson.*;
import model.characters.Character;
import model.characters.util.CharacterFactory;
import model.shapes.Shape;

import java.lang.reflect.Type;
import java.util.Stack;

public class CharacterAdapter implements JsonSerializer<Character>,
        JsonDeserializer<Character> {

    private ShapeAdapter shapeAdapter = null;

    public CharacterAdapter() {
        shapeAdapter = new ShapeAdapter();
    }
    @Override
    public JsonElement serialize(Character character, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonCharacter = new JsonObject();
        jsonCharacter.addProperty("Height",character.getHeight());
        jsonCharacter.addProperty("Width",character.getWidth());
        jsonCharacter.addProperty("LeftStackXInset",character.getLeftStackXInset());
        jsonCharacter.addProperty("LeftStackYInset",character.getLeftStackYInset());
        jsonCharacter.addProperty("RightStackXInset",character.getRightStackXInset());
        jsonCharacter.addProperty("RightStackYInset",character.getRightStackYInset());
        jsonCharacter.addProperty("XProperty",character.getxProperty().doubleValue());
        jsonCharacter.addProperty("YProperty",character.getyProperty().doubleValue());
        jsonCharacter.addProperty("TranslateXProperty",character.getTranslateXProperty().doubleValue());
        jsonCharacter.addProperty("TranslateYProperty",character.getTranslateYProperty().doubleValue());
        jsonCharacter.addProperty("ImageURL",character.getUrl());
        jsonCharacter.addProperty("Key",character.getKey());
        JsonElement jsonArray;
        jsonArray = jsonSerializationContext.serialize(character.getLeftStack().toArray());
        jsonCharacter.add("LeftStackShapes",jsonArray);
        jsonArray = jsonSerializationContext.serialize(character.getRightStack().toArray());
        jsonCharacter.add("RightStackShapes",jsonArray);
        return jsonCharacter;
    }

    @Override
    public Character deserialize(JsonElement jsonElement, Type type,
                                 JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String key = jsonObject.getAsJsonPrimitive("Key").getAsString();

        Character character = CharacterFactory.getInstance().
                createCharacter(key);
        character.setKey(key);
        character.setUrl(jsonObject.getAsJsonPrimitive("ImageURL").getAsString());
        character.setHeight(jsonObject.getAsJsonPrimitive("Height").getAsDouble());
        character.setWidth(jsonObject.getAsJsonPrimitive("Width").getAsDouble());
        character.setLeftStackXInset(jsonObject.getAsJsonPrimitive("LeftStackXInset").getAsDouble());
        character.setLeftStackYInset(jsonObject.getAsJsonPrimitive("LeftStackYInset").getAsDouble());
        character.setRightStackXInset(jsonObject.getAsJsonPrimitive("RightStackXInset").getAsDouble());
        character.setRightStackYInset(jsonObject.getAsJsonPrimitive("RightStackYInset").getAsDouble());
        character.setxProperty(jsonObject.getAsJsonPrimitive("XProperty").getAsDouble());
        character.setyProperty(jsonObject.getAsJsonPrimitive("YProperty").getAsDouble());
        character.setTranslateXProperty(jsonObject.getAsJsonPrimitive("TranslateXProperty").getAsDouble());
        character.setTranslateYProperty(jsonObject.getAsJsonPrimitive("TranslateYProperty").getAsDouble());
        Stack<Shape> leftStack = new Stack<>();
        Stack<Shape> rightStack = new Stack<>();
        JsonArray shapesArray = jsonObject.getAsJsonArray("LeftStackShapes");
        for (JsonElement jsonShape : shapesArray) {
            Shape shape = jsonDeserializationContext.deserialize(jsonShape,Shape.class);
            leftStack.push(shape);
        }
        character.setLeftStack(leftStack);
        shapesArray = jsonObject.getAsJsonArray("RightStackShapes");
        for (JsonElement jsonShape : shapesArray) {
            Shape shape = jsonDeserializationContext.deserialize(jsonShape,Shape.class);
            rightStack.push(shape);
        }
        character.setRightStack(rightStack);
        return character;
    }
}
