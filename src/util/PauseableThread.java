package util;

public abstract class PauseableThread extends Thread {
    public abstract void pauseThread();

    public abstract void resumeThread();
}
