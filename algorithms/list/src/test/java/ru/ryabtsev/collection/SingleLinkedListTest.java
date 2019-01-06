package ru.ryabtsev.collection;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals( 0, integerList.size() );
        Assert.assertTrue( integerList.isEmpty() );
    }

    private void resetList() {
        integerList = new SingleLinkedList<>();
    }

    @Test
    public void addMethodTest() {
        resetList();
        integerList.add(1);
        Assert.assertEquals( 1, integerList.size() );
        integerList.add(3);
        Assert.assertEquals( 2, integerList.size() );
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
        Assert.assertEquals(2, array.length );
        Assert.assertEquals( 1, ((Integer) array[0]).intValue());
        Assert.assertEquals( 3, ((Integer) array[0]).intValue());
    }

    @Test
    public void toArrayInPlaceMethodTest() {
        resetList();
        Integer[] array = new Integer[2];
        integerList.add(1);
        array = integerList.toArray(array);
        Assert.assertEquals( 2, array.length );
        Assert.assertTrue(new Integer(1).equals(array[0]));
        Assert.assertNull( array[1] );
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

    @Test
    public void addToPositionMethodTest() {
        resetList();
        integerList.add(2);
        integerList.add(0, 0);
        integerList.add( 1, 1);
        Assert.assertEquals( 3, integerList.size() );
            for(int i = 0; i < integerList.size(); ++i) {
                Assert.assertEquals(i, integerList.get(i).intValue() );
            }
        }


}
