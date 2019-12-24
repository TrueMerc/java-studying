package ru.ryabtsev.polymorphism;

public class Triangle implements Shape {
    private static final String NAME = "Triangle";

    private final double firstSideLength;
    private final double secondSideLength;
    private final double thirdSideLength;

    public Triangle(double firstSideLength, double secondSideLength, double thirdSideLength) {

        boolean error = (firstSideLength >= secondSideLength + thirdSideLength) ||
                (secondSideLength >= firstSideLength + thirdSideLength) ||
                (thirdSideLength >= secondSideLength + firstSideLength);

        if(error) {
            throw new RuntimeException("Triangle has wrong side sized.");
        }

        this.firstSideLength = firstSideLength;
        this.secondSideLength = secondSideLength;
        this.thirdSideLength = thirdSideLength;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public double area() {
        // Use Heron's formula
        final double halfPerimeter = perimeter() / 2.;
        return Math.sqrt(
                halfPerimeter *
                (halfPerimeter - firstSideLength) *
                (halfPerimeter - secondSideLength) *
                (halfPerimeter - thirdSideLength)
        );
    }

    @Override
    public double perimeter() {
        return firstSideLength + secondSideLength + thirdSideLength;
    }
}
