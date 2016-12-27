package model;

import util.Score;

public class Player  {

    private Score score;
    private String name;

    public Player() {
        score = new Score();
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return this.name;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return this.score;
    }

}
