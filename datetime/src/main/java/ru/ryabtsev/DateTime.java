package ru.ryabtsev;

import java.time.LocalDateTime;

public class DateTime {

    private static final double NANOSECONDS_IN_SECOND = 1e9;

    private final Date date;
    private final Time time;

    /**
     * Constructs date/time object.
     * @param date given date.
     * @param time given time.
     */
    public DateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public static DateTime now() {
        final LocalDateTime dateTime = LocalDateTime.now();
        final Date date = new Date(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
        final Time time = new Time(dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(),
                (double)dateTime.getNano() / NANOSECONDS_IN_SECOND );
        return new DateTime(date, time);
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonth();
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public int getHours() {
        return time.getHours();
    }

    public int getMinutes() {
        return time.getMinutes();
    }

    public int getSeconds() {
        return time.getSeconds();
    }

    public double getPrecisionalSeconds() {
        return time.getPrecisionalSeconds();
    }

    @Override
    public String toString() {
        return date.toString() + ' ' + time.toString();
    }
}
