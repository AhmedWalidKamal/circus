package behaviour.difficultyLevels;


public class MediumLevel implements Strategy {


    private static final double TRANSITION_DURATION=0.0002;
    private static final double HORIZONTAL_DURATION=0.6;
    private static final double FALLING_TIME=0.15;
    //private static final int CREATION_INTERVAL=;

    @Override
    public int getCreationInterval() {
        return 0;
    }

    @Override
    public double getTransitionDuration() {
        return TRANSITION_DURATION;
    }
    @Override
    public double getFallingTime() {
        return FALLING_TIME;
    }

    @Override
    public double getHorizontalDuration() {
        return HORIZONTAL_DURATION;
    }
}
