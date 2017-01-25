package behaviour.difficultyLevels;

public interface Level {

    public long getCreationInterval();
    public double getTransitionDuration();
    public double getFallingRate();
    public double getHorizontalDuration();
    public String getLevelKey();
}
