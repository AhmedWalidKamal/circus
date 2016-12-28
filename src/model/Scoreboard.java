package model;

import java.util.Set;
import java.util.TreeSet;

public class Scoreboard  {

    private Set<Player> scoreboard;

    public Scoreboard() {
        scoreboard = new TreeSet<>();
    }

    public void setScoreboard(Set<Player> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void addNewPlayer(Player player) {

        //TODO check for the existence of a player in a cleaner way + handling the exceptions properly.

        if (scoreboard.contains(player)) {
            throw new RuntimeException("Player exists");
        } else {
            scoreboard.add(player);
        }
    }

    //TODO Test the sorting implementation of the Scoreboard
}