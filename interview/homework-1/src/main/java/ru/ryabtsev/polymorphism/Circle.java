package ru.ryabtsev.polymorphism;

public class Circle implements Shape {

    private static final String NAME = "Circle";
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        final double perimeterMultiplier = 2.;
        return perimeterMultiplier * Math.PI * radius;
    }
}
