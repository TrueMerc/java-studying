package ru.ryabtsev.se.exception;

/**
 * Exception of this type should be thrown when test suite initialization failed.
 */
public class UninitializedTestSuiteException extends TestSuiteException {
    private static final String DEFAULT_DESCRIPTION = "Test suite initialization was failed!";

    public UninitializedTestSuiteException() {
        super( DEFAULT_DESCRIPTION );
    }

    public UninitializedTestSuiteException( String message ) {
        super( message );
    }

}
