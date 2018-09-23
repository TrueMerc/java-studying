package ru.ryabtsev.se;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import ru.ryabtsev.se.exception.*;

/**
 * Unit test for MyArrayExceptionClass and its subclasses.
 */
public class MyArrayExceptionTest
{
    final Accumulator accumulator = new Accumulator();



    /**
     * Tests exception if input array has wrong rows number.
     * */
    @Test
    public void testWrongRowsNumber()
    {
        String[][] array = { { "1", "2", "3", "4" }, { "5", "6", "7", "8" } };

        try {
            accumulator.accumulate(array);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests exception if input array has wrong columns number.
     * */
    @Test
    public void testWrongColumnsNumber()
    {
        String[][] array = { { "1", "2", "3", "4" },
                             { "5", "6", "7", "8" },
                             { "9", "10", "11", "12" },
                             { "13", "14", "15" } };

        try {
            accumulator.accumulate(array);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests exception if input array has wrong columns number.
     * */
    @Test
    public void testWrongData()
    {
        String[][] array = { { "1", "2", "3", "4" },
                             { "5", "6", "7", "8" },
                             { "9", "A", "11", "12" },
                             { "13", "14", "15", "16" } };

        try {
            accumulator.accumulate(array);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            System.out.println( "Exception from cell in row " + e.getRow() + " and in column " + e.getColumn() );
        }
    }

    /**
     * Tests no exception if input array has right rows and columns number.
     * */
    @Test
    public void testRightArraySize()
    {
        String[][] array = { { "1", "2", "3", "4" },
                             { "5", "6", "7", "8" },
                             { "9", "10", "11", "12" },
                             { "13", "14", "15", "16" } };

        try {
            accumulator.accumulate(array);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests no exception and correct calculation results if arrays have right size and data.
     * */
    @Test
    public void testAccumulateMethodAccuracy()
    {
        String[][] zeroes = { { "0", "0", "0", "0" },
                              { "0", "0", "0", "0" },
                              { "0", "0", "0", "0" },
                              { "0", "0", "0", "0" } };

        String[][] unitMatrix = { { "1", "0", "0", "0" },
                                  { "0", "1", "0", "0" },
                                  { "0", "0", "1", "0" },
                                  { "0", "0", "0", "1" } };

        String[][] array = { { "1", "2", "3", "4" },
                             { "5", "6", "7", "8" },
                             { "9", "10", "11", "12" },
                             { "13", "14", "15", "16" } };

        final int expectedResult = 136;
        try {
            Assert.assertTrue( 0 == accumulator.accumulate( zeroes ) );
            Assert.assertTrue( accumulator.DEFAULT_ROWS_NUMBER == accumulator.accumulate( unitMatrix ) );
            Assert.assertTrue( expectedResult == accumulator.accumulate(array) );
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }
}
