package model.save.writer;

import java.io.IOException;

import model.save.ModelMemento;

public interface Writer {
    void saveMemento(ModelMemento memento, String path) throws IOException;
}
