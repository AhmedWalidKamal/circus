package model.save.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Player;
import model.Timer;
import model.save.ModelMemento;
import model.save.util.CharacterAdapter;
import model.save.util.PlayerAdapter;
import model.save.util.ShapeAdapter;
import model.save.util.TimerAdapter;
import model.shapes.Shape;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader implements Reader {

    private Gson gson;

    public JSONReader() {
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
    public ModelMemento loadMemento(String path) throws IOException {
        loadDefaultClasses();
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader(path));
        return gson.fromJson(bufferedReader,ModelMemento.class);
    }

    private void loadDefaultClasses() {
        try {
            Class.forName("model.shapes.Lego");
            Class.forName("model.shapes.Plate");
            Class.forName("model.characters.supportedCharacters.GreenClown");
            Class.forName("model.characters.supportedCharacters.RedClown");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
