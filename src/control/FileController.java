package control;

import model.save.ModelMemento;
import model.save.reader.ProtoReader;
import model.save.reader.Reader;
import model.save.writer.ProtoWriter;
import model.save.writer.Writer;

import java.io.IOException;

public class FileController {
    private MainController mainController = null;
    private Writer writer = null;
    private Reader reader = null;

    public FileController(final MainController mainController) {
        this.mainController = mainController;
        writer = new ProtoWriter();
        reader = new ProtoReader();
    }

    public void save(final ModelMemento memento, String path) throws IOException {
        writer.saveMemento(memento, path);
    }

    public ModelMemento load(final String path) throws IOException {
        ModelMemento memento = reader.loadMemento(path);
        return memento;
    }

}
