package test;

import behaviour.keyBinding.KeyMap;

import java.util.Observable;

public abstract class Player extends Observable {
    public abstract void setState(PlayerState state);

    public abstract PlayerState getState();

    public abstract KeyMap getKeyMap();
}
