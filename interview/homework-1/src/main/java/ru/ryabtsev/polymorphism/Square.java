package ru.ryabtsev.polymorphism;

public class Square implements Shape {

    private static final String NAME = "Square";
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public double area() {
        return sideLength * sideLength;
    }

    @Override
    public double perimeter() {
        final double sides = 4.;
        return sides * sideLength;
    }
}
