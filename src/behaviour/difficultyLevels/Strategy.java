package behaviour.difficultyLevels;

public interface Strategy {


    public int getCreationInterval();
    public double getTransitionDuration();
    public double getFallingTime();
    public double getHorizontalDuration();


}
