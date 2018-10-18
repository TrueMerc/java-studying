package ru.ryabtsev.se.boxes;

import ru.ryabtsev.se.interfaces.Weightable;

import java.util.ArrayList;

public class Box <T extends Weightable> implements Weightable {
    private final ArrayList<T> items = new ArrayList<>(0);

    public void add(T item) {
        items.add( item );
    }

    public boolean compare(Box box) {
        return Float.compare( getWeight(), box.getWeight() ) == 0;
    }

    public void dropTo( Box<T> box ) {
        for( T item : items ) {
            box.add( item );
        }
        items.clear();
    }


    public float getWeight() {
        float result = 0.f;
        for( T item : items ) {
            result += item.getWeight();
        }
        return result;
    }

    public int getItemsNumber() {
        return items.size();
    }

    @Override
    public String toString() {
        return "Items count: " + getItemsNumber() + ", Weight: " + getWeight();
    }
}
