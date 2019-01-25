package ru.ryabtsev.algorithms;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple MainApplication.
 */
public class HashMapTest
{
    /**
     * Tests 'add' method.
     */
    @Test
    public void addElementTest() {
        HashMap<Integer, Integer> map = new HashMap();

        map.put(1, 3);

        Assert.assertEquals(1, map.size());
        Assert.assertEquals(new Integer(3), map.get(1));

        map.put(null, 4);
        Assert.assertEquals(2, map.size());
        Assert.assertEquals(new Integer(4), map.get(null));

        Assert.assertEquals(new Integer(4), map.put(null, 5));
        Assert.assertEquals(2, map.size());
        Assert.assertEquals(new Integer(5), map.get(null));

        Assert.assertEquals(new Integer(3), map.put(1, 7));
        Assert.assertEquals(2, map.size());
        Assert.assertEquals(new Integer(7), map.get(1));
    }
}
