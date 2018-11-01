package ru.ryabtsev.se;

/**
 * Race stage. It is a base class for all race stage types.
 */
public abstract class Stage {

    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}