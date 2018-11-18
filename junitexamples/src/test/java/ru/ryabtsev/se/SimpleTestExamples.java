package ru.ryabtsev.se;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple JUnitExamplesApplication.
 */
public class SimpleTestExamples
{
    // Test for task 2.
    @Test(expected = RuntimeException.class)
    public void arrayWithoutTerminationDigit()
    {
        int[] array = { 0, 0, 0, 0, 0 };

        ArrayProcessor.removeAllBeforeLastTerminationNumber( array );
    }

    // Test for task 2.
    @Test
    public void terminationDigitInTheMiddle() {
        int[] array = { 1, 3, 4, 5, 7 };
        int[] newArray = ArrayProcessor.removeAllBeforeLastTerminationNumber( array );

        int startIndex = array.length / 2 + 1;
        for(int i = startIndex; i < array.length; ++i) {
            //System.out.println( "array[" + i +"] = " + array[i] + ", newArray[" + (i - startIndex) +"] = " + newArray[i - startIndex]);
            Assert.assertTrue( array[i] == newArray[i - startIndex] );
        }
    }

    // Test for task 2.
    @Test
    public void terminationDigitAtTheEnd() {
        int[] array = { 4, 4, 4, 5, 4 };
        int[] newArray = ArrayProcessor.removeAllBeforeLastTerminationNumber( array );

        Assert.assertTrue( newArray.length == 0 );
    }

    // Test for task 2.
    @Test
    public void homeworkDataTest() {
        int[] array = {  1, 2, 4, 4, 2, 3, 4, 1, 7 };
        int[] newArray = ArrayProcessor.removeAllBeforeLastTerminationNumber( array );

        Assert.assertTrue( newArray.length == 2);
        Assert.assertTrue( newArray[0] == 1);
        Assert.assertTrue( newArray[1] == 7);
    }

    // Test for task 3.
    @Test(expected = RuntimeException.class)
    public void arrayContainsUnexpectedNumber() {
        int[] array = { 1, 4, 5, 4, 1 };
        ArrayProcessor.checkThatArrayContainsUnitsAndFours( array );
    }

    // Test for task 3.
    @Test
    public void arrayContainsOnlyUnits() {
        int[] array = { 1, 1, 1, 1, 1 };
        Assert.assertFalse( ArrayProcessor.checkThatArrayContainsUnitsAndFours( array ) );
    }

    // Test for task 3.
    @Test
    public void arrayContainsOnlyFours() {
        int[] array = { 4, 4, 4, 4, 4 };
        Assert.assertFalse( ArrayProcessor.checkThatArrayContainsUnitsAndFours( array ) );
    }

    // Test for task 3.
    @Test
    public void arrayContainsOneUnitAndFours() {
        int[] array = { 4, 1, 4, 4, 4 };
        Assert.assertTrue( ArrayProcessor.checkThatArrayContainsUnitsAndFours( array ) );
    }

    // Test for task 3.
    @Test
    public void arrayContainsOneFourAndUnits() {
        int[] array = { 1, 1, 1, 4, 1 };
        Assert.assertTrue( ArrayProcessor.checkThatArrayContainsUnitsAndFours( array ) );
    }
}
