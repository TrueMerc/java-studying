package ru.ryabtsev.algorithms;

/**
 * Main 'Recursion' theme homework application.
 */
public class MainApplication
{
    public static void main( String[] args )
    {
        processPowerOfNumber();
    }

    private static void processPowerOfNumber() {
        System.out.println("Calculating the first twenty powers of number 2...");
        double base = 2.;
        for(int i = 0; i < 20; ++i) {
            System.out.print(new Power(base, i).asDouble() + " ");
        }
        System.out.println();
    }
}
