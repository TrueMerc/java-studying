package ru.ryabtsev.se.exception;

/**
 * Exception of this type should be thrown when test suite finalization failed.
 */
public class FinalizationFailureTestSuiteException extends TestSuiteException {
    private static final String DEFAULT_DESCRIPTION = "Test suite finalization was failed!";

    public FinalizationFailureTestSuiteException() {
        super(DEFAULT_DESCRIPTION);
    }

    public FinalizationFailureTestSuiteException(String description) {
        super(description);
    }
}
