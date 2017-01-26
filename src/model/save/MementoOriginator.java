package model.save;

public interface MementoOriginator {
    void collectMemento(final ModelMemento memento);
    void loadFromMemento(final ModelMemento memento);
}
