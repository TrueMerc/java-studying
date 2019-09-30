package ru.ryabtsev;

/**
 * Implements date representation.
 */
public class Date {

    private final int year;
    private final byte month;
    private final byte dayOfMonth;

    public Date(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = (byte)month;
        this.dayOfMonth = (byte)dayOfMonth;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    boolean equals(Date other) {
        return (year == other.year) && (month == other.month) && (dayOfMonth == other.dayOfMonth);
    }

    @Override
    public String toString() {
        return new String() + year + '-' + month + '-' + dayOfMonth;
    }
}
