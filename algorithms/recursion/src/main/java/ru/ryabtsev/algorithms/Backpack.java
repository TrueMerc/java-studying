package ru.ryabtsev.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents 'backpack' abstraction for backpack task solution.
 */
public class Backpack {

    final List<Item> items = new ArrayList<>();
    private int[][] nutritionValuesTable;
    int maximalWeight;

    /**
     * Constructs new backpack with given maximal weight.
     * @param maximalWeight maximal weight of this backpack.
     */
    Backpack(int maximalWeight) {
        this.maximalWeight = maximalWeight;
    }

    /**
     * Adds new item to this backpack if it is possible. Possibility depends on current backpack weight,
     * backpack maximal weight and weight of this item.
     * @return true if item is added successfully or false if item isn't added.
     */
    void add(final Item item) {
        items.add(item);
    }

    /**
     * Gets the optimal set of items for given knapsack maximal weight.
     */
    public List<Item> getBestItems() {
        final List<Item> result = new ArrayList<>();
        fillNutritionValuesTable();
        addItemsToResult(result, items.size(), maximalWeight);
        return result;
    }

    private void fillNutritionValuesTable() {
        int itemsCount = items.size();
        nutritionValuesTable = new int[items.size() + 1][maximalWeight + 1];

        for(int i = 0; i <= maximalWeight; ++i) {
            nutritionValuesTable[0][i] = 0;
        }

        for(int i = 0; i <= itemsCount; ++i) {
            nutritionValuesTable[i][0] = 0;
        }

        for(int i = 1; i <= itemsCount; ++i) {
            int weight = items.get(i - 1).getWeight();
            int nutritionalValue = items.get(i - 1).getNutritionalValue();
            for(int w = 1; w <= maximalWeight; ++w) {
                if(w >= weight) {
                    nutritionValuesTable[i][w] = Math.max(
                            nutritionValuesTable[i - 1][w],
                            nutritionValuesTable[i - 1][w - weight] + nutritionalValue
                    );
                }
                else {
                    nutritionValuesTable[i][w] = nutritionValuesTable[i - 1][w];
                }
            }
        }
    }

    private void addItemsToResult(List<Item> result, int itemIndex, int weightIndex) {
        if(nutritionValuesTable[itemIndex][weightIndex] == 0) {
            return;
        }
        if(nutritionValuesTable[itemIndex - 1][weightIndex] == nutritionValuesTable[itemIndex][weightIndex]) {
            addItemsToResult(result, itemIndex - 1, weightIndex);
        }
        else {
            addItemsToResult(result, itemIndex - 1, weightIndex - items.get(itemIndex - 1).getWeight());
            result.add(items.get(itemIndex - 1));
        }
    }
}
