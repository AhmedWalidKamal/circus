package controller;

import behaviour.difficultyLevels.EasyLevel;
import behaviour.difficultyLevels.HardLevel;
import behaviour.difficultyLevels.MediumLevel;
import behaviour.difficultyLevels.Strategy;

public class LevelsController {
    /**
     * Reference to {@link MainController}.
     */
    private MainController mainController = null;

    private Strategy currentLevel;
    /**
     * Constructs a new {@link LevelsController}
     * @param mainController
     */

    public LevelsController(final MainController mainController) {
        this.mainController = mainController;
        currentLevel=new EasyLevel();///current level is set to easy by default.
    }

    public void chooseLevel(String level){
        /*choose level based on level chosen from options in the main menu
        but set the default level to easy unless changed*/
        if (level.equals("EASY"))
           currentLevel=new EasyLevel();
        else if(level.equals("MEDIUM"))
            currentLevel=new MediumLevel();
        else if(level.equals("HARD"))
            currentLevel=new HardLevel();


    }

}
