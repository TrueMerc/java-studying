package ru.ryabtsev.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents 'backpack' abstraction for backpack task solution.
 */
public class Backpack {

    final List<Item> items = new ArrayList<>();
    float maximalWeight;
    float currentWeight;

    /**
     * Constructs new backpack.
     */
    Backpack(float maximalWeight) {
        this.maximalWeight = maximalWeight;
        this.currentWeight = 0f;
    }

    /**
     * Adds new item to this backpack if it is possible. Possibility depends on current backpack weight,
     * backpack maximal weight and weight of this item.
     * @return true if item is added successfully or false if item isn't added.
     */
    boolean add(final Item item) {
        if( item.getWeight() + currentWeight > maximalWeight ) {
            return false;
        }
        items.add(item);
        currentWeight += item.getWeight();
        return true;
    }
}
