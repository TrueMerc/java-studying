package ru.ryabtsev.se;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple MultithreadingApplication and its components.
 */
public class MultithreadingApplicationTest
{
    /**
     * Tests method fills array with units. Single thread version.
     */
    @Test
    public void fillWithUnitsSingleThreadedTest()
    {
        final int arraySize = 100;
        final float array[] = new float[ arraySize ];
        final SingleThreadedWorker stWorker = new SingleThreadedWorker();

        stWorker.fillWithUnits( array );

        for( float element : array ) {
            Assert.assertTrue( ( new Float(element) ).compareTo( new Float( 1. ) ) == 0 );
        }
    }

    /**
     * Tests method fills array with units. Multiple threads version.
     */
    @Test
    public void fillWithUnitsMultiThreadedTest()
    {
        final int arraySize = 100;
        final float array[] = new float[ arraySize ];
        final MultithreadedWorker mtWorker = new MultithreadedWorker();

        mtWorker.fillWithUnits( array );

        for( int i = 0; i < array.length; ++i ) {
            try {
                Assert.assertTrue((new Float( array[i] )).compareTo(new Float(1.)) == 0);
            }
            catch( AssertionError e ) {
                System.out.println( "Element isn't equal to 1: " + array[i] + " at position " + i );
                e.printStackTrace();
            }
        }
    }


}
