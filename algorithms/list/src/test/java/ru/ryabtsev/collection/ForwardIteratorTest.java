package ru.ryabtsev.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Test for ForwardList.ForwardIterator class.
 */
public class ForwardIteratorTest extends ForwardListTestBase {

    @Test
    public void iteratorMethodTest() {
        resetList();
        fillMainList();
        Iterator<Integer> it = integerList.iterator();

        int i;
        for(i = 0; it.hasNext(); ++i ) {
            Assert.assertEquals(i, it.next().intValue());
        }
        Assert.assertEquals(i, integerList.size());
    }

    @Test
    public void iteratorRemoveMethodTest() {
        resetList();
        fillMainList();
        Iterator<Integer> it = integerList.iterator();

        while(it.hasNext()) {
            it.next();
            it.remove();
        }
        Assert.assertEquals(0, integerList.size());
    }
}
