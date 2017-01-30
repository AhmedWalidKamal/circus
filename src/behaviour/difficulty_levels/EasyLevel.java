package behaviour.difficulty_levels;

public class EasyLevel implements Level {
    private static final double TRANSITION_DURATION = 0.0005;
    private static final double HORIZONTAL_DURATION = 0.8;
    private static final double FALLING_RATE = 0.15;
    private static final long CREATION_INTERVAL = 2000;
    private static final String KEY = "EASY";

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
