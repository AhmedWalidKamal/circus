package control;

import behaviour.difficultyLevels.EasyLevel;
import behaviour.difficultyLevels.HardLevel;
import behaviour.difficultyLevels.Level;
import behaviour.difficultyLevels.NormalLevel;
import model.save.ModelMemento;
import model.save.MementoOriginator;

public class LevelsController implements MementoOriginator {
    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    private Level currentLevel = null;
    /**
     * Constructs a new {@link LevelsController}
     * @param mainController
     */

    public LevelsController(final MainController mainController) {
        this.mainController = mainController;
        currentLevel = new EasyLevel(); ///current level is set to easy by default.
    }

    public void chooseLevel(final String level){
        /*choose level based on level chosen from options in the main menu
        but set the default level to easy unless changed*/
        switch (level.toUpperCase()) {
            case "EASY":
                currentLevel = new EasyLevel();
                break;
            case "NORMAL":
                currentLevel = new NormalLevel();
                break;
            case "HARD":
                currentLevel = new HardLevel();
                break;
            default:
                break;
        }
    }
    public Level getDifficultyLevel(){
        return this.currentLevel;
    }

    @Override
    public void collectMemento(final ModelMemento memento) {
        memento.setDifficultyLevel(currentLevel.getLevelKey());
    }

    @Override
    public void loadFromMemento(final ModelMemento memento) {
        chooseLevel(memento.getDifficultyLevel());
    }
}
