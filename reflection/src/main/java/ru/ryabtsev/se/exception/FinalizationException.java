package ru.ryabtsev.se.exception;

/**
 * Exception of this type should be thrown when test suite finalization failed.
 */
public class FinalizationException extends TestSuiteException {
    private static final String DEFAULT_DESCRIPTION = "Test suite finalization was failed!";

    public FinalizationException() {
        super(DEFAULT_DESCRIPTION);
    }

    public FinalizationException(String description) {
        super(description);
    }
}
