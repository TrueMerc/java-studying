package ru.ryabtsev.se.others;

import org.junit.Test;

/**
 * Tests 'final' keyword in different situations.
 */
public class FinalModifierTest {
    @Test
    public void modifyNotFinalString() {
        String string = "String";
        String newString = "New string";

        modify( string, newString );

        System.out.println( "After modification: ");
        System.out.println( "New string value: " + string );
    }

    // Don't uncoment this. It wouldn't be compile, because: "final parameter oldString may not be assigned"
    //private void modify(final String oldString, final String newString) {
    //    oldString = newString;
    //}

    private void modify(String oldString, final String newString) {
        oldString = new String("New String");
    }
}
