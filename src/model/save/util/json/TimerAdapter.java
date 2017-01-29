package model.save.util.json;


import com.google.gson.*;
import model.Timer;

import java.lang.reflect.Type;

public class TimerAdapter implements JsonSerializer<Timer>,JsonDeserializer<Timer> {

    @Override
    public JsonElement serialize(Timer timer, Type type, JsonSerializationContext jsonSerializationContext) {
       JsonObject jsonTimer = new JsonObject();
       jsonTimer.addProperty("Timer Value",timer.getCurrentTime());
       return jsonTimer;
    }

    @Override
    public Timer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        Timer timer = new Timer(jsonElement.getAsJsonObject().getAsJsonPrimitive("Timer Value").getAsInt());
        return timer;
    }


}
