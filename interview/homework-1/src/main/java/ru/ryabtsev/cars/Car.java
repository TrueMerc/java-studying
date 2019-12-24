package ru.ryabtsev.cars;

abstract public class Car implements Openable, Moveable, Startable, Stopable {
    public Engine engine;
    private String color;
    private String name;

    @Override
    public void start() {
        System.out.println("Car is starting.");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping.");
    }

    @Override
    public void move() {
        System.out.println("Car is moving.");
    }

    @Override
    public void open() {
        System.out.println("Car is open.");
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
