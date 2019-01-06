package ru.ryabtsev.collection;

import static org.junit.Assert.assertTrue;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Unit test for SingleLinkedList class.
 */
public class SingleLinkedListTest
{
    private List<Integer> integerList;

    @Test
    public void emptyListCreationTest()
    {
        resetList();
        Assert.assertTrue( 0 == integerList.size() );
        Assert.assertTrue( integerList.isEmpty() );
    }

    private void resetList() {
        integerList = new SingleLinkedList<>();
    }

    @Test
    public void addMethodTest() {
        resetList();
        integerList.add(1);
        Assert.assertTrue( 1 == integerList.size() );
        integerList.add(3);
        Assert.assertTrue( 2 == integerList.size() );
    }

    @Test
    public void containsMethodTest() {
        resetList();
        Assert.assertFalse( integerList.contains(0) );
        integerList.add(1);
        integerList.add(3);
        Assert.assertTrue( integerList.contains(1) );
        Assert.assertFalse( integerList.contains(2) );
        Assert.assertTrue( integerList.contains(3) );
    }

    @Test
    public void toArrayMethodTest() {
        resetList();
        integerList.add(1);
        integerList.add(3);
        Object[] array = integerList.toArray();
        Assert.assertTrue(2 == array.length );
        Assert.assertTrue( 1 == (Integer) array[0]);
        Assert.assertTrue( 3 == (Integer) array[1]);
    }

    @Test
    public void toArrayInPlaceMethodTest() {
        resetList();
        Integer[] array = new Integer[2];
        integerList.add(1);
        array = integerList.toArray(array);
        Assert.assertTrue(array.length == 2 );
        Assert.assertTrue(new Integer(1).equals(array[0]));
        Assert.assertTrue( null == array[1]);
        integerList.add(2);
        integerList.add(3);
        array = integerList.toArray(array);
        Assert.assertTrue(array.length == 3 );
        Assert.assertTrue(new Integer(1).equals(array[0]));
        Assert.assertTrue(new Integer(2).equals(array[1]));
        Assert.assertTrue(new Integer(3).equals(array[2]));
    }

    @Test
    public void removeMethodTest() {
        resetList();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        int size = integerList.size();
        Assert.assertTrue( integerList.remove(new Integer(2)) );
        Assert.assertFalse( integerList.remove(new Integer(4)) );
        Assert.assertTrue( integerList.size() == size - 1);
        integerList.remove(new Integer(1));
        integerList.remove(new Integer( 3));
        Assert.assertTrue( integerList.size() == size - 3);
    }

    @Test
    public void getMethodTest() {
        resetList();
        sequentiallyFillList();
        for(int i = 9; i > -1; --i) {
           Assert.assertTrue( i == integerList.get(i));
        }
    }

    private void sequentiallyFillList(int number, int step) {
        for(int i = 0; i < number; ++i) {
            integerList.add(i * step);
        }
    }

    private void sequentiallyFillList() {
        sequentiallyFillList(10, 1);
    }

    @Test
    public void setMethodTest() {
        resetList();
        sequentiallyFillList();
        for(int i = 0; i < integerList.size(); ++i) {
            Assert.assertTrue( i == integerList.set(i, i * 2));
            Assert.assertTrue( 2 * i == integerList.get(i));
        }
    }


}
