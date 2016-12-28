package model;

import util.Score;

public class Player implements Comparable<Player> {

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

    @Override
    public int compareTo(Player player) {

        if (score.compareTo(player.getScore()) != 0) {
            return score.compareTo(player.getScore());
        } else {
            return name.compareTo(player.getPlayerName());
        }
    }
}
