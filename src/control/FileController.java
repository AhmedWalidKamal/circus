package control;

import model.save.ModelMemento;
import model.save.reader.JSONReader;
import model.save.reader.ProtoReader;
import model.save.reader.Reader;
import model.save.writer.JSONWriter;
import model.save.writer.ProtoWriter;
import model.save.writer.Writer;

import java.io.IOException;

public class FileController {
    private Writer writerProtoBuff = null;
    private Reader readerProtoBuff = null;
    private Writer writerJSON = null;
    private Reader readerJSON = null;

    public FileController() {
        writerProtoBuff = new ProtoWriter();
        readerProtoBuff = new ProtoReader();
        writerJSON = new JSONWriter();
        readerJSON = new JSONReader();
    }

    public void save(final ModelMemento memento, String path) throws IOException {
        if (path.endsWith(".protobuff")) {
            writerProtoBuff.saveMemento(memento, path);
        } else {
            writerJSON.saveMemento(memento, path);
        }


    }

    public ModelMemento load(final String path) throws IOException {
        if (path.endsWith(".protobuff")) {
            return readerProtoBuff.loadMemento(path);
        } else {
            return readerJSON.loadMemento(path);
        }
    }
}
