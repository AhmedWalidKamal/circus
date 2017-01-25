package model.save;

import model.Player;

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

    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    public double getTime() {
        return time;
    }

    public void setTime(final double time) {
        this.time = time;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
