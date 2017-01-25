package model.save.reader;

import java.io.IOException;

import model.save.ModelMemento;

public interface Reader {
    ModelMemento loadMemento(String path) throws IOException;
}
