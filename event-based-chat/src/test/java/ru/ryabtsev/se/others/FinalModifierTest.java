package ru.ryabtsev.se.others;

import org.junit.Test;

/**
 * Tests 'final' keyword in different situations.
 */
public class FinalModifierTest {
    @Test
    void modifyNotFinalString() {
        String string = "String";
        String newString = "New string";
    }

    private void modify(final String oldString, final String newString) {
        oldString = newString;
    }
}
