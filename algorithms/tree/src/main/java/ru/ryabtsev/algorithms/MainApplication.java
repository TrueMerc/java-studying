package ru.ryabtsev.algorithms;

/**
 * Main application class which implements the sixth homework task.
 */
public class MainApplication {

    private static final int TREES_NUMBER = 100000;
    private static final int TREE_MAX_DEPTH = 6;

    public static void main(String[] args) {

        Random random = new Random(-100, 100);
        BinaryTree tree;
        int balancedTreesCounter = 0;

        long time = System.currentTimeMillis();
        for(int i = 0; i < TREES_NUMBER; ++i) {
            tree = new BinaryTree();
            while( tree.depth() < TREE_MAX_DEPTH) {
                tree.add( random.getValue() );
            }
            balancedTreesCounter += tree.isBalanced() ? 1 : 0;
        }
        time = System.currentTimeMillis() - time;

        System.out.println("Calculation time (seconds): " + (double)time / 1000.);
        System.out.println("Trees number: " + TREES_NUMBER);
        System.out.println("Balanced trees number: " + balancedTreesCounter);
        System.out.println("Balanced trees percent: " + 100f * (float)balancedTreesCounter / TREES_NUMBER);
    }
}
