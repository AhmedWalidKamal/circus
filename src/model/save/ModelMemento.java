package model.save;

import model.Player;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

//TODO: Get a better name.
public class ModelMemento {
    private List<Player> players = null;
    private double time;
    private LocalDateTime localDateTime = null;

    public ModelMemento(List<Player> players, double time, LocalDateTime localDateTime) {
        this.players = players;
        this.time = time;
        this.localDateTime = localDateTime;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public double getTime() {
        return time;
    }
}
