package model.save;

import java.time.LocalDateTime;
import java.util.List;

import model.Player;
import model.Timer;

public class ModelMemento {
    private List<Player> players = null;
    private Timer timer;
    private LocalDateTime localDateTime = null;

    public ModelMemento(final List<Player> players, final Timer timer, final LocalDateTime localDateTime) {
        this.players = players;
        this.timer = timer;
        this.localDateTime = localDateTime;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    public Timer getTime() {
        return this.timer;
    }

    public void setTimer(final Timer timer) {
        this.timer = timer;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
