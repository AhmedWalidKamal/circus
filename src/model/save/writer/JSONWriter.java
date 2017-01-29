package model.save.writer;

import com.google.gson.*;
import model.Player;
import model.Timer;
import model.save.ModelMemento;
import model.save.util.CharacterAdapter;
import model.save.util.PlayerAdapter;
import model.save.util.ShapeAdapter;
import model.save.util.TimerAdapter;
import model.shapes.Shape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter implements Writer{

    private Gson gson;

    public JSONWriter() {
        gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .registerTypeAdapter(Player.class,new PlayerAdapter())
                .registerTypeAdapter(Character.class,new CharacterAdapter())
                .registerTypeAdapter(Shape.class,new ShapeAdapter())
                .registerTypeAdapter(Timer.class,new TimerAdapter())
                .setPrettyPrinting()
                .create();
    }



    @Override
    public void saveMemento(ModelMemento memento, String path) throws IOException {
        FileWriter writer = new FileWriter(new File(path));
        writer.write(gson.toJson(memento));
        writer.close();
    }





}
