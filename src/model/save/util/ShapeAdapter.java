package model.save.util;

import com.google.gson.*;
import model.Color;
import model.shapes.Shape;
import model.shapes.util.ShapeFactory;

import java.lang.reflect.Type;

public class ShapeAdapter implements JsonSerializer<Shape>,JsonDeserializer<Shape> {

    public ShapeAdapter() {

    }

    @Override
    public JsonElement serialize(Shape shape, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonShape = new JsonObject();
        jsonShape.addProperty("Key",shape.getKey());
        jsonShape.addProperty("State",shape.getState().toString());
        jsonShape.addProperty("Image URL",shape.getUrl());
        jsonShape.addProperty("Width",shape.getWidth());
        jsonShape.addProperty("Height",shape.getHeight());
        jsonShape.addProperty("Color",shape.getColor().toString());
        jsonShape.addProperty("X",shape.getX());
        jsonShape.addProperty("Y",shape.getY());
        jsonShape.addProperty("Translate X",shape.getTranslateX().doubleValue());
        jsonShape.addProperty("Translate Y",shape.getTranslateY().doubleValue());
        return jsonShape;
    }

    @Override
    public Shape deserialize(JsonElement jsonElement, Type type,
                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String key = jsonObject.getAsJsonPrimitive("Key").getAsString();
        Color color = getColor(jsonObject.getAsJsonPrimitive("Color").getAsString());
        Shape shape = ShapeFactory.getInstance().createShape(key,color);
        shape.setkey(key);
        shape.setState(getState(jsonObject.getAsJsonPrimitive("State").getAsString()));
        shape.setUrl(jsonObject.getAsJsonPrimitive("Image URL").getAsString());
        shape.setWidth(jsonObject.getAsJsonPrimitive("Width").getAsDouble());
        shape.setHeight(jsonObject.getAsJsonPrimitive("Height").getAsDouble());
        shape.setColor(color);
        shape.setX(jsonObject.getAsJsonPrimitive("X").getAsDouble());
        shape.setY(jsonObject.getAsJsonPrimitive("Y").getAsDouble());
        shape.setTranslateXProperty(jsonObject.getAsJsonPrimitive("Translate X").getAsDouble());
        shape.setTranslateYProperty(jsonObject.getAsJsonPrimitive("Translate Y").getAsDouble());
        return shape;
    }

    private Color getColor(final String color) {
        for (Color enumColor : Color.values()) {
            if (enumColor.toString().equalsIgnoreCase(color)) {
                return enumColor;
            }
        }
        return null;
    }

    private Shape.State getState(final String state) {
        for (Shape.State enumState : Shape.State.values()) {
            if (enumState.toString().equalsIgnoreCase(state)) {
                return enumState;
            }
        }
        return null;
    }


}
