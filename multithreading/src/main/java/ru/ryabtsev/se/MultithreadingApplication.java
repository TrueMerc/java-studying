package ru.ryabtsev.se;

/**
 * Hello world!
 *
 */
public class MultithreadingApplication
{
    final private static int ARRAY_SIZE = 10000000;

    public static void main( String[] args )
    {
        final float stArray[] = new float[ ARRAY_SIZE ];
        final Worker stWorker = new SingleThreadedWorker();
        final Function function = new HomeworkFunction();
        processData( stArray, stWorker, function );

        final float mtArray[] = new float[ ARRAY_SIZE ];
        final Worker mtWorker = new MultithreadedWorker();
        processData( mtArray, mtWorker, function );

        compareArrays( stArray, mtArray );
    }

    private static void processData(float[] array, Worker worker, Function function) {
        printStartMessage( worker, "filling array with unit values.");
        long measurementStartTime = System.currentTimeMillis();
        worker.fillWithUnits( array );
        long executionTime = System.currentTimeMillis() - measurementStartTime;
        printExecutionTime( executionTime );

        printStartMessage( worker, "filling array with function values.");
        measurementStartTime = System.currentTimeMillis();
        worker.fillWithFunction( array, function );
        executionTime = System.currentTimeMillis() - measurementStartTime;
        printExecutionTime( executionTime );
    }

    private static void printStartMessage( Worker  worker, String workDescription ) {
        String workerTypeString = (worker instanceof MultithreadedWorker) ? "Multithreaded worker" :
                                                                            "Single threaded worker";
        System.out.println( workerTypeString + " starts " + workDescription );
    }

    private static void printExecutionTime( long executionTimeInMilliseconds ) {
        System.out.println( "Operation execution time " + executionTimeInMilliseconds + " milliseconds." );
    }

    private static void compareArrays( float[] first, float[] second ) {
        if( first.length == second.length) {
            for( int i = 0; i < first.length; ++i ) {
                if( ( new Float( first[i] ) ).compareTo( new Float( second[i] ) ) != 0 ) {
                    System.out.println("Values are different:" + first[i] + " " + second[i] + " at index " + i );
                    break;
                }
            }
        }
    }
}
