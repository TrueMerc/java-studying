package ru.ryabtsev.se;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple JUnitExamplesApplication.
 */
public class SimpleTestExamples
{
    @Test(expected = RuntimeException.class)
    public void arrayWithoutTerminationDigit()
    {
        int[] array = { 0, 0, 0, 0, 0 };

        ArrayProcessor.removeAllBeforeLastTerminationNumber( array );
    }

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

    @Test
    public void terminationDigitAtTheEnd() {
        int[] array = { 4, 4, 4, 5, 4 };
        int[] newArray = ArrayProcessor.removeAllBeforeLastTerminationNumber( array );

        Assert.assertTrue( newArray.length == 0 );
    }

    @Test
    public void homeworkDataTest() {
        int[] array = {  1, 2, 4, 4, 2, 3, 4, 1, 7 };
        int[] newArray = ArrayProcessor.removeAllBeforeLastTerminationNumber( array );

        Assert.assertTrue( newArray.length == 2);
        Assert.assertTrue( newArray[0] == 1);
        Assert.assertTrue( newArray[1] == 7);
    }
}
