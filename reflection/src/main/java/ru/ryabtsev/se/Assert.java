package ru.ryabtsev.se;

import ru.ryabtsev.se.exception.AssertionFailureException;

/**
 * Assertions class.
 */
public class Assert {

    public static void assertTrue( String message, boolean condition) {
        if( !condition ) {
            fail( message );
        }
    }

    public static void assertTrue( boolean condition ) {
        assertTrue( null, condition );
    }

    public static void assertFalse( String message, boolean condition ) {
        assertTrue( message, !condition );
    }

    public static void assertFalse( boolean condition ) {
        assertFalse( null, condition );
    }

    public static void fail( String message ) {
        throw (message != null) ? new AssertionFailureException( message ) : new AssertionFailureException("");
    }
}
