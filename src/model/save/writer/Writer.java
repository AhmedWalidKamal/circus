package model.save.writer;

import model.save.ModelMemento;

import java.time.LocalDateTime;

public interface Writer {
    void saveMemento(ModelMemento memento);
    ModelMemento loadMemento(LocalDateTime localDateTime);
}
