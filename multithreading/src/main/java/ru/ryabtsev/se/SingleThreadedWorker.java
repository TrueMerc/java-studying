package ru.ryabtsev.se;

/**
 * Class performs operations with arrays using single thread.
 */
public class SingleThreadedWorker {
    /**
     *
     * @param array - fill all elements of array with unit value.
     */
    public void fillWithUnits(float array[]) {
        for( int i = 0; i < array.length; ++i ) {
            array[i] = 1;
        }
    }

    /**
     *
     * @param array - fill all elements of array with value which is calculated by {@link Function} object.
     * @param function - function object calculates new values in array cells.
     */
    public void fillWithFunction(float array[], Function function) {
        for( int i = 0; i < array.length; ++i ) {
            array[i] = function.value( array[i], i );
        }
    }
}
