package ru.ryabtsev.collection;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Unit test for SingleLinkedList class.
 */
public class SingleLinkedListTest
{
    @Test
    public void emptyListCreationTest()
    {
        List<Integer> integerList = new SingleLinkedList<>();
        Assert.assertTrue( 0 == integerList.size() );
        Assert.assertTrue( integerList.isEmpty() );
    }

    @Test
    public void addMethodTest() {
        List<Integer> integerList = new SingleLinkedList<>();
        integerList.add(1);
        Assert.assertTrue( 1 == integerList.size() );
        integerList.add(3);
        Assert.assertTrue( 2 == integerList.size() );
    }

    @Test
    public void containsMethodTest() {
        List<Integer> integerList = new SingleLinkedList<>();
        Assert.assertFalse( integerList.contains(0) );
        integerList.add(1);
        integerList.add(3);
        Assert.assertTrue( integerList.contains(1) );
        Assert.assertFalse( integerList.contains(2) );
        Assert.assertTrue( integerList.contains(3) );
    }

    @Test
    public void toArrayMethodTest() {
        List<Integer> integerList = new SingleLinkedList<>();
        integerList.add(1);
        integerList.add(3);
        Object[] array = integerList.toArray();
        Assert.assertTrue(2 == array.length );
        Assert.assertTrue( 1 == (Integer) array[0]);
        Assert.assertTrue( 3 == (Integer) array[1]);
    }
}
