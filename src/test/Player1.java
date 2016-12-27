package test;

import behaviour.keyBinding.KeyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Player1 extends Player {
    private PlayerState state;
    private List<Observer> observerList = null;
    private KeyMap keyMap = null;

    public Player1() {
        state = PlayerState.NOT_MOVING;
        observerList = new ArrayList<>();
//        keyMap = new KeyMap();
    }

    @Override
    public void setState(PlayerState state) {
        this.state = state;
    }

    @Override
    public PlayerState getState() {
        return this.state;
    }

    @Override
    public KeyMap getKeyMap() {
        return null;
    }


    @Override
    public synchronized void addObserver(final Observer o) {
        observerList.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList) {
            o.notify();
        }
    }
}
