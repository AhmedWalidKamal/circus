package behaviour.difficultyLevels;


public class NormalLevel implements Level {
    private static final double TRANSITION_DURATION = 0.0002;
    private static final double HORIZONTAL_DURATION = 0.6;
    private static final double FALLING_RATE = 0.2;
    private static final long CREATION_INTERVAL = 1000;
    private static final String KEY = "NORMAL";

    @Override
    public long getCreationInterval() {
        return CREATION_INTERVAL;
    }

    @Override
    public double getTransitionDuration() {
        return TRANSITION_DURATION;
    }
    @Override
    public double getFallingRate() {
        return FALLING_RATE;
    }

    @Override
    public double getHorizontalDuration() {
        return HORIZONTAL_DURATION;
    }

    @Override
    public String getLevelKey() {
    	return KEY;
    }
}
