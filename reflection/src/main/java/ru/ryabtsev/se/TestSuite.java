package ru.ryabtsev.se;

import ru.ryabtsev.se.annotation.AfterSuite;
import ru.ryabtsev.se.annotation.BeforeSuite;
import ru.ryabtsev.se.annotation.Priority;
import ru.ryabtsev.se.annotation.Test;

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
    @Priority(value = 10)
    public void firstTest() {
        System.out.println("First test.");

    }

    @Test
    public void secondTest() {
        System.out.println("Second test.");
    }

    @Test
    @Priority(value = 10)
    public void thirdTest() {
        System.out.println("Third test.");
    }
}
