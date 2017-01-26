package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapePool;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import logs.LoggingManager;
import model.Player;
import model.characters.Character;
import model.save.MementoOriginator;
import model.save.ModelMemento;
import model.shapes.Shape;
import util.PauseableThread;

/**
 * Acts as a control to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class ShapesController extends PauseableThread implements MementoOriginator {
    /**
     * {@link MainController} reference.
     */
    private MainController mainController = null;
    private boolean paused = false;
    private List<PauseableThread> runningThreads = null;
    private Map<Shape, ImageView> shapeImageViewMap = null;

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
        runningThreads = new ArrayList<>();
        shapeImageViewMap = new HashMap<>();
    }

    @Override
    public void run() {
        int counter = 0;
        try {
            Class.forName("model.shapes.Plate");
            Class.forName("model.shapes.Lego");
        } catch (final ClassNotFoundException e) {
            LoggingManager.getInstance().info("FAILED TO LOAD CLASS");
            e.printStackTrace();
        }
        while (true) {
            if (!paused) {
                final Thread plateThread = new Thread("plate" + counter) {
                    @Override
                    public void run() {
                        Shape shape = ShapePool.getInstance().create();
                        ImageView shapeImage = new ImageView(shape.getUrl());
                        bindImageWithShape(shape, shapeImage);
                        ShapeContext context
                        = new ShapeContext(shape, shapeImage, mainController);
                        context.handle();
                    }
                };
                plateThread.setDaemon(true);
                Platform.runLater(plateThread);
                counter = (counter + 1) % 1000;
                try {
                    sleep(mainController.getLevelsController()
                            .getDifficultyLevel().getCreationInterval());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void bindImageWithShape(final Shape shape, final ImageView imageView) {
        shape.getXProperty().bind(imageView.xProperty());
        shape.getYProperty().bind(imageView.yProperty());
        shape.getTranslateX().bind(imageView.translateXProperty());
        shape.getTranslateY().bind(imageView.translateYProperty());
        shape.setWidth(imageView.getImage().getWidth());
        shape.setHeight(imageView.getImage().getHeight());
    }

    public void addRunningShapeThread(final PauseableThread thread) {
        runningThreads.add(thread);
    }

    public void removeRunningShapeThread(final PauseableThread thread) {
        runningThreads.remove(thread);
    }

    @Override
    public void pauseThread() {
        for (PauseableThread thread : runningThreads) {
            thread.pauseThread();
        }
        paused = true;
    }

    public void putFetchedShape(final ImageView imageView, final Shape shape) {
        shapeImageViewMap.put(shape, imageView);
    }

    public void removeShape(final Shape shape) {
        shapeImageViewMap.remove(shape);
    }

    public ImageView getCorrespondingShape(final Shape shape) {
        return shapeImageViewMap.get(shape);
    }

    @Override
    public void resumeThread() {
        for (PauseableThread thread : runningThreads) {
            thread.resumeThread();
        }
        paused = false;
    }

    @Override
    public void collectMemento(final ModelMemento memento) {
        return;
    }

    @Override
    public void loadFromMemento(final ModelMemento memento) {
        for (Player player : memento.getPlayers()) {
            for (Shape shape : player.getCharacter().getLeftStack()) {
                setUpShape(shape, player);
            }
            for (Shape shape : player.getCharacter().getRightStack()) {
                setUpShape(shape, player);
            }
        }
    }

    private void setUpShape(final Shape shape, final Player player) {
        ImageView imageView = new ImageView(shape.getUrl());
        copyShape(imageView, shape);
        bindImageWithShape(shape, imageView);
        bindImageWithCharacter(imageView, player.getCharacter());
        putFetchedShape(imageView, shape);
        mainController.getViewController().addToRootPane(imageView);
    }

    private void bindImageWithCharacter(final ImageView imageView, final Character
            character) {
        imageView.translateXProperty().bind(character.getTranslateXProperty());
        imageView.translateYProperty().bind(character.getTranslateYProperty());
    }

    private void copyShape(final ImageView imageView, final Shape shape) {
        imageView.setX(shape.getX());
        imageView.setY(shape.getY());
        imageView.setTranslateX(shape.getTranslateX().doubleValue());
        imageView.setTranslateY(shape.getTranslateY().doubleValue());
    }
}