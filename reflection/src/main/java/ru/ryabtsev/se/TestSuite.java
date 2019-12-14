package ru.ryabtsev.se;

import ru.ryabtsev.se.annotation.AfterSuite;
import ru.ryabtsev.se.annotation.BeforeSuite;
import ru.ryabtsev.se.annotation.Priority;
import ru.ryabtsev.se.annotation.Test;
import ru.ryabtsev.se.exception.AssertionFailureException;
import ru.ryabtsev.se.exception.TestSuiteException;

/**
 * Test suite example.
 */
public class TestSuite {

    @BeforeSuite
    public void initialization() {
        System.out.println("Initialization called.");
    }

    @AfterSuite
    public void finalization() {
        System.out.println("Finalization called.");
    }

    @Test
    public void firstTest() {
        System.out.println("First test.");
        Assert.assertTrue( 2 == 2 );
    }

    @Test
    @Priority(value = 5)
    public void secondTest() {
       System.out.println("Second test.");
       Assert.assertFalse( 2 == 1 );
    }

    @Test
    @Priority(value = 10)
    public void thirdTest() {
        System.out.println("Third test.");
        Assert.assertFalse( false );
    }

    @Test(expected = AssertionFailureException.class)
    @Priority(value = 7)
    public void fourthTest() {
        System.out.println("Fourth test.");
        throw new TestSuiteException("Test suite exception.");
    }

}
