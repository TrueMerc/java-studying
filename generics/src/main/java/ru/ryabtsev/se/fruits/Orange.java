package ru.ryabtsev.se.fruits;

import ru.ryabtsev.se.interfaces.Weightable;

public class Orange implements Fruit, Weightable {

    public String getName() {
        return "Orange";
    }

    public float getWeight() {
        return 1.5f;
    }
}