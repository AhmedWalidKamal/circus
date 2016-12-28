package util;


public class Time implements Comparable<Time>{

    private int seconds;
    private int minutes;
    private int hours;

    public Time() {

    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


    public int getSeconds() {
        return this.seconds;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getHours() {
        return this.hours;
    }

    @Override
    public int compareTo(Time time) {
        if (hours == time.getHours()) {
            if (minutes == time.getMinutes()) {
                if (seconds == time.getSeconds()) {
                    return 0;
                } else {
                    if (seconds > time.getSeconds()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            } else {
                if (minutes > time.getMinutes()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        } if (hours > time.getHours()) {
            return -1;
        } else {
            return 1;
        }
    }
}
