package ru.ryabtsev.algorithms;

public class Random {

    private int left;
    private int right;


    public Random(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return (int)Math.round((double)left + (double)(right - left) * Math.random());
    }

    public int getLeftBorder() {
        return left;
    }

    public int getRightBorder() {
        return right;
    }

}
