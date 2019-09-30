package ru.ryabtsev.algorithms;

/**
 * Backpack item.
 */
public class Item {

    private int weight;
    private int nutritionalValue;

    /**
     * Constructs new backpack item with given weight and given nutritional value.
     * @param weight
     * @param nutritionalValue
     */
    public Item(int weight, int nutritionalValue) {
        this.weight = weight;
        this.nutritionalValue = nutritionalValue;
    }

    /**
     * Returns the item's weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     *  Returns the item's nutritional value.
     */
    public int getNutritionalValue() {
        return nutritionalValue;
    }

    /**
     * Returns the string representation.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("weight: " + this.weight + ", nutrition value: " + this.nutritionalValue);
        return stringBuilder.toString();
    }
}
