package util;

public abstract class PausableThread extends Thread {
    public abstract void pauseThread();

    public abstract void resumeThread();
}
