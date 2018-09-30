package ru.ryabtsev.se;

/**
 * Hello world!
 *
 */
public class MultithreadingApplication
{
    final private static int ARRAY_SIZE = 10000000;
    //final private static int ARRAY_SIZE = 1000;
    final private static int HALF_ARRAY_SIZE = ARRAY_SIZE / 2;

    public static void main( String[] args )
    {

        float stArray[] = new float[ ARRAY_SIZE ];
        final SingleThreadedWorker stWorker = new SingleThreadedWorker();
        final Function function = new HomeworkFunciton();

        System.out.println("Single threaded worker start filling array with unit values.");
        long executionTime = System.currentTimeMillis();
        stWorker.fillWithUnits( stArray );
        executionTime = System.currentTimeMillis() - executionTime;
        System.out.println( "Operation execution time " + executionTime  );

        System.out.println("Single threaded worker start filling array with function values.");
        executionTime = System.currentTimeMillis();
        stWorker.fillWithFunction( stArray, function );
        executionTime = System.currentTimeMillis() - executionTime;
        System.out.println( "Operation execution time " + executionTime );


        float mtArray[] = new float[ ARRAY_SIZE ];
        final MultiThreadedWorker mtWorker = new MultiThreadedWorker();
        System.out.println("Multiple threaded worker start filling array with unit values.");
        executionTime = System.currentTimeMillis();
        mtWorker.fillWithUnits( mtArray );
        executionTime = System.currentTimeMillis() - executionTime;
        System.out.println( "Operation execution time " + executionTime  );

        System.out.println("Multiple threaded worker start filling array with function values.");
        executionTime = System.currentTimeMillis();
        mtWorker.fillWithFunction( mtArray, function );
        executionTime = System.currentTimeMillis() - executionTime;
        System.out.println( "Operation execution time " + executionTime );

        if( stArray.length == mtArray.length) {
            for( int i = 0; i < stArray.length; ++i ) {
                if( ( new Float( stArray[i] ) ).compareTo( new Float( mtArray[i] ) ) != 0 ) {
                    System.out.println("Values are different:" + stArray[i] + " " + mtArray[i] + " at index " + i );
                    break;
                }
            }
        }
    }
}
