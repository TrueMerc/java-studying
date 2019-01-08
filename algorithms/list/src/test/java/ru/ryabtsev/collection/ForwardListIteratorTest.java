package ru.ryabtsev.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ListIterator;

/**
 * Test for ForwardList.ListIterator class.
 */
public class ForwardListIteratorTest  extends  ForwardListTestBase {

    @Test
    public void listIteratorTest() {
        resetList();
        fillMainList();
        ListIterator<Integer> it = integerList.listIterator();

        Assert.assertEquals(0, it.nextIndex());
        it.set(100);

        Assert.assertEquals(100, integerList.get(0).intValue());
        it.add(101);
        Assert.assertEquals(11, integerList.size());
        System.out.println(integerList);
        Assert.assertEquals(101, integerList.get(0).intValue());
        Assert.assertEquals(100, it.next().intValue());
        it.add(102);
        System.out.println(integerList);
        Assert.assertEquals(102, integerList.get(2).intValue());
    }
}
