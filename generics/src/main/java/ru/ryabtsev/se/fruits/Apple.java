package ru.ryabtsev.se.fruits;

import ru.ryabtsev.se.interfaces.Weightable;

public class Apple implements Fruit, Weightable {

    public String getName() {
        return "Apple";
    }

    public float getWeight() {
        return 1.f;
    }
}
