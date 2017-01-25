package util;

public class Score implements Comparable<Score>{

    private Integer currentScore;

    public Score() {
        currentScore = new Integer(0);
    }

    public Score(final int currentScore) {
    	this.currentScore = currentScore;
    }

    public int getCurrentScore(){
        return this.currentScore;
    }

    public void setCurrentScore(final int currentScore){
        this.currentScore = currentScore;
    }

    @Override
    public int compareTo(final Score score) {
        return currentScore.compareTo(score.getCurrentScore());
    }
}
