package ru.ryabtsev.se;

/**
 * Task which fills array with units.
 */
class FillWithUnitsTask implements Runnable {

    private final float array[];

    /**
     * Constructs new task for given array.
     * @param array - array which will filled with units.
     */
    FillWithUnitsTask( float array[] ) {
        this.array = array;
    }

    @Override
    public void run() {
        for( int i = 0; i < array.length; ++i ) {
            this.array[i] = 1;
        }
    }
}

/**
 * Task which fills array by specified function values.
 */
class FillWithFunctionValuesTask implements Runnable {
    private final float array[];
    private final int initialIndex;
    private final Function function;

    /**
     * Constructs new task for given array.
     * @param array - array which will filled with units.
     * @param initialIndex - index in big array where the part starts.
     * @param function - function which calculates values in array cells.
     */
    FillWithFunctionValuesTask( float array[], int initialIndex, Function function ) {
        this.array = array;
        this.initialIndex = initialIndex;
        this.function = function;
    }

    @Override
    public void run() {
        for( int i = 0; i < array.length; ++i ) {
            array[i] = function.value( array[i], initialIndex + i );
        }
    }
}

public class MultiThreadedWorker implements Worker {
    public final static int THREADS_NUMBER = 2;
    private final Thread[] threads;
    private float arrays[][];

    MultiThreadedWorker() {
        threads = new Thread[ THREADS_NUMBER ];
    }

    public void fillWithUnits( float[] array ) {
        // FIXME This code correctly works only if array.length % THREADS_NUMBER = 0.
        arrays = new float[ THREADS_NUMBER ][ array.length / THREADS_NUMBER];

        for( int i = 0; i < THREADS_NUMBER; ++i ) {
            FillWithUnitsTask task = new FillWithUnitsTask( arrays[i] );
            threads[i] = new Thread( task );
            threads[i].run();
        }

        joinAllThreads();

//        for( int i = 0, destinationIndex = 0; i < arrays.length;  ++i) {
//            System.arraycopy( arrays[i], 0, array, destinationIndex, arrays[i].length );
//            destinationIndex = destinationIndex + arrays[i].length;
//        }
        copyResults( array );
    }

    public void fillWithFunction( float[] array, Function function ) {
        // FIXME This code correctly works only if array.length % THREADS_NUMBER = 0.
        arrays = new float[ THREADS_NUMBER ][ array.length / THREADS_NUMBER];


        for( int i = 0, sourceIndex = 0; i < THREADS_NUMBER; ++i ) {
            System.arraycopy( array, sourceIndex, arrays[i], 0, arrays[i].length );
            FillWithFunctionValuesTask task = new FillWithFunctionValuesTask( arrays[i], sourceIndex, function );
            threads[i] = new Thread( task );
            threads[i].run();
            sourceIndex += arrays[i].length;
        }

        joinAllThreads();


//        for( int i = 0, destinationIndex = 0; i < arrays.length;  ++i) {
//            System.arraycopy( arrays[i], 0, array, destinationIndex, arrays[i].length );
//            destinationIndex = destinationIndex + arrays[i].length;
//        }
        copyResults( array );
    }

    private void joinAllThreads() {
        for( int i = 0; i < threads.length; ++i ) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyResults( float[] array) {
        for( int i = 0, destinationIndex = 0; i < arrays.length;  ++i) {
            System.arraycopy( arrays[i], 0, array, destinationIndex, arrays[i].length );
            destinationIndex = destinationIndex + arrays[i].length;
        }
    }
}
