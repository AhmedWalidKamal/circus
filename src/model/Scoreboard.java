package model;

import util.PlayerComparator;

import java.util.Set;
import java.util.TreeSet;

public class Scoreboard  {

    private Set<Player> scoreboard;

    public Scoreboard() {
        scoreboard = new TreeSet<>(new PlayerComparator());
    }

    public void setScoreboard(Set<Player> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void addNewPlayer(Player player) {

        if (scoreboard.contains(player)) {
            throw new RuntimeException("Player exists");
        } else {
            scoreboard.add(player);
        }
    }
}