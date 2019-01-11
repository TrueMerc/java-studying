package ru.ryabtsev.algorithms;

import java.util.List;

/**
 * Main 'Recursion' theme homework application.
 */
public class MainApplication
{
    public static void main( String[] args )
    {
        processPowerOfNumber();
        processKnapsackProblem();
    }

    private static void processPowerOfNumber() {
        double base = 2.;
        System.out.println("Calculating the first twenty powers of number " + base + "...");
        for(int i = 0; i < 20; ++i) {
            System.out.print(new Power(base, i).asDouble() + " ");
        }
        System.out.println();
    }

    private static void processKnapsackProblem() {
        System.out.println("Solving the knapsack problem...");
        Backpack backpack = new Backpack(21);
        backpack.add(new Item(3, 1));
        backpack.add(new Item(4, 6));
        backpack.add(new Item(5, 4));
        backpack.add(new Item(8, 7));
        backpack.add(new Item(9, 6));
        backpack.add(new Item(5, 6));
        backpack.add(new Item(7, 8));

        final List<Item> result = backpack.getBestItems();
        System.out.println("Items selected: " + result.size());
        for(int i = 0; i < result.size(); ++i) {
            System.out.println("Item " + i + ": " + result.get(i));
        }
    }
}
