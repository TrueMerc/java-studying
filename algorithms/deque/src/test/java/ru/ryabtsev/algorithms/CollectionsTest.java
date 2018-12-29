package ru.ryabtsev.algorithms;

import org.junit.Test;
import java.lang.ArrayIndexOutOfBoundsException;
import ru.ryabtsev.algorithms.collections.Stack;

/**
 * Unit test for simple MainApplication.
 */
public class CollectionsTest
{
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void stackOverflowTest()
    {
        Stack<Integer> stack = new Stack<>(3);
        for(int i = 0; i <= stack.depth(); ++i) {
            stack.push(i);
        }
    }
}
