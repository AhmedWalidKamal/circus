package model.save;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.Timer;

public class ModelMemento {
    private List<Player> players = null;
    private Timer timer;
    private LocalDateTime localDateTime = null;
    private String level = null;

    public ModelMemento() {
        players = new ArrayList<>();
        timer = new Timer();
        localDateTime = LocalDateTime.now();
        level = "";
    }
    public ModelMemento(final List<Player> players,
    		final Timer timer,
    		final LocalDateTime localDateTime, final String level) {
        this.players = players;
        this.timer = timer;
        this.localDateTime = localDateTime;
        this.level = level;

    }

    public void setDifficultyLevel(final String level) {
    	this.level = level;
    }

    public String getDifficultyLevel() {
    	return this.level;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    public Timer getTimer() {
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
