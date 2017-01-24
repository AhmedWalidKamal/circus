package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Timer {
    private IntegerProperty currentTimeProperty = null;

    public Timer() {
        currentTimeProperty = new SimpleIntegerProperty(0);
    }

    public Timer(int duration) {
        currentTimeProperty = new SimpleIntegerProperty(duration);
    }

    public IntegerProperty getCurrentTimeProperty() {
        return currentTimeProperty;
    }

    public int getCurrentTime() {
        return currentTimeProperty.getValue();
    }
}
