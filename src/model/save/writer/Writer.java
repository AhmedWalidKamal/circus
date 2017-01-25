package model.save.writer;

import model.save.ModelMemento;

public interface Writer {
    void saveMemento(ModelMemento memento);
}
