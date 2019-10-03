package ru.ryabtsev.algorithms;

/**
 * Generates random integer numbers in given range.
 */
public class Random {

    private int left;
    private int right;

    /**
     * Constructs new random integer numbers generator with given range borders.
     * @param left the left border of this range.
     * @param right the right border of this range.
     */
    public Random(int left, int right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Retuns random integer number value.
     * @return random integer number value.
     */
    public int getValue() {
        return (int)Math.round((double)left + (double)(right - left) * Math.random());
    }

    /**
     * Returns the left border of the range where random numbers are generated.
     * @return the left border of range where random numbers are generated.
     */
    public int getLeftBorder() {
        return left;
    }

    /**
     * Returns the right border of the range where random numbers are generated.
     * @return the right border of range where random numbers are generated.
     */
    public int getRightBorder() {
        return right;
    }

}
