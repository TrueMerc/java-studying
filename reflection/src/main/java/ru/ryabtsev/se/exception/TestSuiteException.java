package ru.ryabtsev.se.exception;

/**
 * Base class for exception which can be thrown at test suite execution.
 */
public class TestSuiteException extends RuntimeException {

    public TestSuiteException( String description ) {
        super( description );
    }


}
