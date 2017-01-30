package behaviour.difficulty_levels;

public interface Level {
    /**
     * Gets interval between a shape creation and the next.
     * @return interval of shape creation.
     */
    long getCreationInterval();

    /**
     * Gets duration to shape's falling transition.
     * @return falling transition duration.
     */
    double getTransitionDuration();

    /**
     * Gets falling time rate.
     * @return falling rate.
     */
    double getFallingRate();

    /**
     * Gets duration of a horizontal duration.
     * @return horizontal duration transition.
     */
    double getHorizontalDuration();

    /**
     * Gets key defining level.
     * @return key.
     */
    String getLevelKey();
}
