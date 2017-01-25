package model.save.reader;

import java.time.LocalDateTime;

import model.save.ModelMemento;

public interface Reader {
    ModelMemento loadMemento(LocalDateTime localDateTime);
}
