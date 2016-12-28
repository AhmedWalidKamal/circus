package util;

public class Score implements Comparable<Score>{

    private int currentScore;
    private Time currentTime;

    public Score() {

    }

    public int getPoints(){
        return this.currentScore;
    }

    public void setPoints(int currentScore){
        this.currentScore = currentScore;
    }

    public void setTime(Time currentTime) {
        this.currentTime = currentTime;
    }

    public Time getTime() {
        return this.currentTime;
    }

    @Override
    public int compareTo(Score score) {
        if (currentScore == score.getPoints()) {
            return  currentTime.compareTo(score.getTime());
        } else {
            if (currentScore > score.getPoints()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
