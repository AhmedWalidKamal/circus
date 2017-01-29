package model.save.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.Player;
import model.save.ModelMemento;
import model.save.util.CharacterAdapter;
import model.save.util.PlayerAdapter;
import model.save.util.ShapeAdapter;
import model.shapes.Shape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class JSONWriter implements Writer{

    private Gson gson;

    public JSONWriter() {
        gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .registerTypeAdapter(Player.class,new PlayerAdapter())
                .registerTypeAdapter(Character.class,new CharacterAdapter())
                .registerTypeAdapter(Shape.class,new ShapeAdapter())
                .setPrettyPrinting()
                .create();
    }



    @Override
    public void saveMemento(ModelMemento memento, String path) throws IOException {
        FileWriter writer = new FileWriter(new File(path));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("GameDifficulty",memento.getDifficultyLevel());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        jsonObject.addProperty("LocalDateTime",memento.getLocalDateTime().format(formatter));
        jsonObject.addProperty("CurrentTime",memento.getTimer().getCurrentTime());
        gson.toJson(jsonObject,writer);
        writer.write(gson.toJson(memento));
        writer.close();
    }





}
