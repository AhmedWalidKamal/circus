package control;

import behaviour.shapes.ShapeContext;
import behaviour.shapes.util.ShapePool;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import logs.LogsManager;
import model.shapes.Shape;
import util.PauseableThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Acts as a control to shapes behavior, using a subroutine that handles
 * creation, falling, fetching... etc and sends data to other controllers
 * accordingly, or directly update the view.
 */
public final class ShapesController extends PauseableThread {
    /**
     * {@link MainController} reference.
     */
    private MainController mainController = null;
    private ShapePool shapePool = null;
    private boolean paused = false;
    private List<PauseableThread> runningThreads = null;
    private Map<Shape, ImageView> shapeImageViewMap = null;

    /**
     * Constructs a new {@link ShapesController}.
     * @param mainController
     */
    public ShapesController(final MainController mainController) {
        this.mainController = mainController;
        this.shapePool = new ShapePool();
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
            LogsManager.getInstance().info("FAILED TO LOAD CLASS");
            e.printStackTrace();
        }
        while (true) {
            if (!paused) {
                final Thread plateThread = new Thread("plate" + counter) {
                    @Override
                    public void run() {
                        Shape shape = shapePool.create();
                        ImageView shapeImage = new ImageView(shape.getUrl());
                        bindImageWithShape(shape, shapeImage);
                        ShapeContext context = new ShapeContext(shape, shapeImage,
                                ShapesController.this,
                                mainController.getViewController(),
                                mainController.getGameUtilController(),
                                mainController.getPlayersController(),
                                mainController.getLevelsController(), shapePool);
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

    private void bindImageWithShape(Shape shape, ImageView imageView) {
        shape.getXProperty().bind(imageView.xProperty());
        shape.getYProperty().bind(imageView.yProperty());
        shape.getTranslateX().bind(imageView.translateXProperty());
        shape.getTranslateY().bind(imageView.translateYProperty());
        shape.setWidth(imageView.getImage().getWidth());
        shape.setHeight(imageView.getImage().getHeight());
    }

    public void addRunningShapeThread(PauseableThread thread) {
        runningThreads.add(thread);
    }

    public void removeRunningShapeThread(PauseableThread thread) {
        runningThreads.remove(thread);
    }

    @Override
    public void pauseThread() {
        for (PauseableThread thread : runningThreads) {
            thread.pauseThread();
        }
        paused = true;
    }

    public void putFetchedShape(ImageView imageView, Shape shape) {
        shapeImageViewMap.put(shape, imageView);
    }

    public void removeShape(Shape shape) {
        shapeImageViewMap.remove(shape);
    }

    public ImageView getCorrespondingShape(Shape shape) {
        return shapeImageViewMap.get(shape);
    }

    @Override
    public void resumeThread() {
        for (PauseableThread thread : runningThreads) {
            thread.resumeThread();
        }
        paused = false;
    }


}