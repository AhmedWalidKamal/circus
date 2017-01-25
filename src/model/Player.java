package model;

import model.characters.Character;
import util.Score;

public class Player implements Comparable<Player> {
    private Score score = null;
    private String name = null;
    private Character character = null;

    public Player(Character character) {
        score = new Score();
        this.character = character;
    }

    public Player(String name, Character character) {
        score = new Score();
        this.name = name;
        this.character = character;
    }


    public Score getScore() {
        return this.score;
    }

    public void setScore(final Score score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Character getCharacter() {
        return this.character;
    }

    public void setCharacter(final Character character) {
        this.character = character;
    }

    @Override
    public int compareTo(final Player player) {
        if (score.compareTo(player.getScore()) != 0) {
            return score.compareTo(player.getScore());
        } else {
            return name.compareTo(player.getName());
        }
    }
}
