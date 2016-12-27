package util;

import util.Time;

public class Score {

    private int currentScore;
    private Time currentTime;

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

}
