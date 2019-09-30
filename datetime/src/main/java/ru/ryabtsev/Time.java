package ru.ryabtsev;

/**
 * Implements time representation.
 */
public class Time {

    private final byte hours;
    private final byte minutes;
    private final byte seconds;
    private final double fractionalSeconds;

    public Time(int hours, int minutes, int seconds, double fractionalSeconds) {
        this.hours = (byte)hours;
        this.minutes = (byte)minutes;
        this.seconds = (byte)seconds;
        this.fractionalSeconds = fractionalSeconds;
    }

    public Time(byte hours, byte minutes, byte seconds) {
        this(hours, minutes, seconds, 0.);
    }

    public byte getHours() {
        return hours;
    }

    public byte getMinutes() {
        return minutes;
    }

    public byte getSeconds() {
        return seconds;
    }

    public double getPrecisionalSeconds() {
        return seconds + fractionalSeconds;
    }

    public Time addSeconds(int seconds) {
        final int hours = seconds / DateTimeConstants.SECONDS_IN_HOUR;
        final int minute = (seconds / DateTimeConstants.SECONDS_IN_MINUTE) % DateTimeConstants.MINUTES_IN_HOUR;
        final int sec =  seconds % DateTimeConstants.SECONDS_IN_MINUTE;

        int newSeconds = getSeconds() + sec;
        int newMinutes = getMinutes() + minute;
        int newHours = getHours() + hours;

        if(newSeconds >= DateTimeConstants.SECONDS_IN_MINUTE) {
            newMinutes++;
            newSeconds %= DateTimeConstants.SECONDS_IN_MINUTE;
        }

        if(newMinutes >= DateTimeConstants.MINUTES_IN_HOUR) {
            newHours++;
            newMinutes %= DateTimeConstants.MINUTES_IN_HOUR;
        }

        newHours %= DateTimeConstants.HOURS_IN_DAY;

        return new Time(newHours, newMinutes, newSeconds, fractionalSeconds);
    }

    public Time addMinutes(int minutes) {
        final int hours = minutes / DateTimeConstants.MINUTES_IN_HOUR;
        final int min =  minutes % DateTimeConstants.MINUTES_IN_HOUR;

        int newMinutes = getMinutes() + min;
        int newHours = getHours() + hours;

        if(newMinutes >= DateTimeConstants.MINUTES_IN_HOUR) {
            newHours++;
            newMinutes %= DateTimeConstants.MINUTES_IN_HOUR;
        }

        newHours %= DateTimeConstants.HOURS_IN_DAY;

        return new Time(newHours, newMinutes, seconds, fractionalSeconds);
    }

    boolean equals(Time other) {
        return (hours == other.hours) && (minutes == other.minutes) && (seconds == other.seconds) &&
                (fractionalSeconds == other.fractionalSeconds);
    }

    @Override
    public String toString() {
        return new String() + hours + ":" + minutes + ":" + seconds;
    }
}
