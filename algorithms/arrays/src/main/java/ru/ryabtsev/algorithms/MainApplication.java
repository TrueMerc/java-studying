package ru.ryabtsev.algorithms;

/**
 * Main  application.
 */
public class MainApplication
{
    private static final int DEFAULT_LENGTH = 1000000;
    private static final int MAX_PRINTABLE_ELEMENTS = 50;

    private static final float MIN_ELEMENT = -1000f;
    private static final float MAX_ELEMENT = 1000f;

    public static void main( String[] args )
    {
        final float[] array = new float[DEFAULT_LENGTH];

        fill( array );

        print( array );

        long millis = System.currentTimeMillis();
        float[] removedElementArray = remove( array, 5 );
        millis = System.currentTimeMillis() - millis;
        System.out.println("Deletion time = " + millis + " milliseconds.");
        print( removedElementArray );

        millis = System.currentTimeMillis();
        float[] insertedElementArray = insert( array, 5, MAX_ELEMENT );
        millis = System.currentTimeMillis() - millis;
        System.out.println("Insertion time = " + millis + " milliseconds.");
        print( insertedElementArray );

        millis = System.currentTimeMillis();
        int index = find( insertedElementArray, MAX_ELEMENT );
        millis = System.currentTimeMillis() - millis;
        System.out.println("Find element time = " + millis + " milliseconds.");
        System.out.println("Element " + MAX_ELEMENT + " found at index " + index + ".");

        millis = System.currentTimeMillis();
        bubbleSorting( array );
        millis = System.currentTimeMillis() - millis;
        System.out.println("Bubble sorting time = " + millis + " milliseconds.");
        print( array );

        millis = System.currentTimeMillis();
        selectionSorting( removedElementArray );
        millis = System.currentTimeMillis() - millis;
        System.out.println("Selection sorting time = " + millis + " milliseconds.");
        print( removedElementArray );


        millis = System.currentTimeMillis();
        insertionSorting( insertedElementArray );
        millis = System.currentTimeMillis() - millis;
        System.out.println("Insertion sorting time = " + millis + " milliseconds.");
        print( insertedElementArray );
    }

    static void fill( float[] array) {
        // Fill array with random numbers.
        for( int i = 0; i < array.length; ++i) {
            array[i] = getRandom();
        }
    }

    static float getRandom() {
        return MIN_ELEMENT + (MAX_ELEMENT - MIN_ELEMENT) * (float)Math.random();
    }

    static void print(float[] array) {
        if( array.length > MAX_PRINTABLE_ELEMENTS ) {
            return;
        }
        for( float element: array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }


    static float[] remove(float[] array, int index) {
        float[] result = new float[array.length - 1];
        for(int i = 0; i < index; ++i) {
            result[i] = array[i];
        }
        for(int i = index + 1; i < array.length; ++i) {
            result[i - 1] = array[i];
        }
        return result;
    }

    static float[] insert(float[] array, int index, float element) {
        float[] result = new float[array.length + 1];
        for( int i = 0; i < index; ++i ) {
            result[i] = array[i];
        }
        result[index] = element;
        for( int i = index + 1; i < result.length; ++i) {
            result[i] = array[i - 1];
        }
        return result;
    }

    static int find(float[] array, float element) {
        for(int i = 0; i < array.length; ++i) {
            if( Float.compare(array[i], element) == 0 ) {
                return i;
            }
        }
        return -1;
    }

    static void swap(float[] array, int index1, int index2) {
        float temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    static void bubbleSorting(float[] array) {
        int out, in;
        for ( out = array.length - 1; out >= 1; --out) {
            for(in = 0; in < out; ++in){
                if (array[in] > array[in + 1]) {
                    swap(array, in, in + 1);
                }
            }
        }
    }

    static void selectionSorting(float[] array) {
        int out, in, mark;
        for(out = 0; out < array.length; ++out) {
            mark = out;
            for (in = out + 1; in < array.length; ++in) {
                if (array[in] < array[mark]) {
                    mark = in;
                }
            }
            swap(array, out, mark);
        }
    }

    static void insertionSorting(float[] array) {
        int in, out;
        for(out = 1; out < array.length; ++out){
            float temp = array[out];
            in = out;
            while(in > 0 && array[in - 1] >=temp){
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }
}
