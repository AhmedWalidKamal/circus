package behaviour.difficultyLevels;

public interface Level {

    long getCreationInterval();
    double getTransitionDuration();
    double getFallingRate();
    double getHorizontalDuration();
    String getLevelKey();
}
