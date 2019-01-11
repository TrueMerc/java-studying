package ru.ryabtsev.algorithms;

/**
 * Backpack item.
 */
public class Item {

    private float weight;
    private float nutritionalValue;

    /**
     * Constructs new backpack item with given weight and given nutritional value.
     * @param weight
     * @param nutritionalValue
     */
    public Item(float weight, float nutritionalValue) {
        this.weight = weight;
        this.nutritionalValue = nutritionalValue;
    }

    /**
     * Returns the item's weight.
     */
    public float getWeight() {
        return weight;
    }

    /**
     *  Returns the item's nutritional value.
     */
    public float getNutritionalValue() {
        return nutritionalValue;
    }
}
