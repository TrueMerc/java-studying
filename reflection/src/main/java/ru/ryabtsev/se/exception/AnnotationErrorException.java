package ru.ryabtsev.se.exception;

public class AnnotationErrorException extends TestSuiteException {

    public AnnotationErrorException(String description) {
        super(description);
    }
}
