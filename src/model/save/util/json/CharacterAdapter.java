package model.save.util.json;

import com.google.gson.*;
import model.characters.Character;
import model.characters.util.CharacterFactory;
import model.shapes.Shape;

import java.lang.reflect.Type;
import java.util.Stack;

public class CharacterAdapter implements JsonSerializer<Character>,
        JsonDeserializer<Character> {
    private ShapeAdapter shapeAdapter;

    public CharacterAdapter() {
        shapeAdapter = new ShapeAdapter();
    }

    @Override
    public JsonElement serialize(Character character, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonCharacter = new JsonObject();
        jsonCharacter.addProperty("Height",character.getHeight());
        jsonCharacter.addProperty("Width",character.getWidth());
        jsonCharacter.addProperty("Left Stack X Inset",character.getLeftStackXInset());
        jsonCharacter.addProperty("Left Stack Y Inset",character.getLeftStackYInset());
        jsonCharacter.addProperty("Right Stack X Inset",character.getRightStackXInset());
        jsonCharacter.addProperty("Right Stack Y Inset",character.getRightStackYInset());
        jsonCharacter.addProperty("X Property",character.getxProperty().doubleValue());
        jsonCharacter.addProperty("Y Property",character.getyProperty().doubleValue());
        jsonCharacter.addProperty("Translate X Property",character.getTranslateXProperty().doubleValue());
        jsonCharacter.addProperty("Translate Y Property",character.getTranslateYProperty().doubleValue());
        jsonCharacter.addProperty("Image URL",character.getUrl());
        jsonCharacter.addProperty("Key",character.getKey());

        JsonArray jsonArray = new JsonArray();
        for (Shape shape : character.getLeftStack()) {
            jsonArray.add(shapeAdapter.serialize(shape,Shape.class,jsonSerializationContext));
        }
        jsonCharacter.add("Left Stack Shapes",jsonArray);
        jsonArray = new JsonArray();
        for (Shape shape : character.getRightStack()) {
            jsonArray.add(shapeAdapter.serialize(shape,Shape.class,jsonSerializationContext));
        }
        jsonCharacter.add("Right Stack Shapes",jsonArray);
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
        character.setUrl(jsonObject.getAsJsonPrimitive("Image URL").getAsString());
        character.setHeight(jsonObject.getAsJsonPrimitive("Height").getAsDouble());
        character.setWidth(jsonObject.getAsJsonPrimitive("Width").getAsDouble());
        character.setLeftStackXInset(jsonObject.getAsJsonPrimitive("Left Stack X Inset").getAsDouble());
        character.setLeftStackYInset(jsonObject.getAsJsonPrimitive("Left Stack Y Inset").getAsDouble());
        character.setRightStackXInset(jsonObject.getAsJsonPrimitive("Right Stack X Inset").getAsDouble());
        character.setRightStackYInset(jsonObject.getAsJsonPrimitive("Right Stack Y Inset").getAsDouble());
        character.setxProperty(jsonObject.getAsJsonPrimitive("X Property").getAsDouble());
        character.setyProperty(jsonObject.getAsJsonPrimitive("Y Property").getAsDouble());
        character.setTranslateXProperty(jsonObject.getAsJsonPrimitive("Translate X Property").getAsDouble());
        character.setTranslateYProperty(jsonObject.getAsJsonPrimitive("Translate Y Property").getAsDouble());
        Stack<Shape> leftStack = new Stack<>();
        Stack<Shape> rightStack = new Stack<>();
        JsonArray shapesArray = jsonObject.getAsJsonArray("Left Stack Shapes");
        for (JsonElement jsonShape : shapesArray) {
            Shape shape = shapeAdapter.deserialize(jsonShape,Shape.class,jsonDeserializationContext);
            leftStack.push(shape);
        }
        character.setLeftStack(leftStack);
        shapesArray = jsonObject.getAsJsonArray("Right Stack Shapes");
        for (JsonElement jsonShape : shapesArray) {
            Shape shape = shapeAdapter.deserialize(jsonShape,Shape.class,jsonDeserializationContext);
            rightStack.push(shape);
        }
        character.setRightStack(rightStack);
        return character;
    }
}
