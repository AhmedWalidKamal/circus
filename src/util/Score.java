package util;

public class Score implements Comparable<Score>{

    private Integer currentScore;

    public Score() {
        currentScore = new Integer(0);
    }

    public int getCurrentScore(){
        return this.currentScore;
    }

    public void setCurrentScore(int currentScore){
        this.currentScore = currentScore;
    }

    @Override
    public int compareTo(Score score) {
        return currentScore.compareTo(score.getCurrentScore());
    }
}
