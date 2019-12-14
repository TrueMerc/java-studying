package ru.ryabtsev.algorithms;

/**
 * Represents the integer power of decimal number.
 */
public class Power {

    private double value;

    /**
     * Constructs power object with given base and exponent.
     * @param base value of the base.
     * @param exponent value of the exponent.
     */
    public Power(double base, int exponent) {
        if(exponent == 0) {
            value = 1.;
        }
        else if(exponent == 1) {
            value = base;
        }
        else {
            double squareRoot = new Power(base, exponent / 2).asDouble();
            value = squareRoot * squareRoot;
            value *= (exponent % 2 != 0) ? base : 1.;
        }
    }

    /**
     * Returns decimal value of 'power' object.
     * @return decimal value of 'power' object.
     */
    public double asDouble() {
        return value;
    }
}
