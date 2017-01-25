package model.save.reader;

import java.io.IOException;
import java.time.LocalDateTime;

import model.save.ModelMemento;

public interface Reader {
    ModelMemento loadMemento(LocalDateTime localDateTime) throws IOException;
}
