package model.save.util;

import com.google.gson.*;
import model.Player;
import model.characters.Character;
import util.Score;

import java.lang.reflect.Type;

public class PlayerAdapter implements JsonSerializer<Player>,JsonDeserializer<Player> {

    @Override
    public JsonElement serialize(Player player, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonPlayer = new JsonObject();
        jsonPlayer.addProperty("PlayerName",player.getName());
        jsonPlayer.addProperty("Score",player.getScore().getCurrentScore());
        jsonPlayer.add("Character",jsonSerializationContext.serialize(player.getCharacter()));
        return jsonPlayer;
    }

    @Override
    public Player deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Player player = new Player();
        player.setName(jsonElement.getAsJsonObject().getAsJsonPrimitive("PlayerName").getAsString());
        player.setScore(new Score(jsonElement.getAsJsonObject().getAsJsonPrimitive("Score").getAsInt()));
        player.setCharacter(jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject().get("Character"), Character.class));
        return player;
    }


}
