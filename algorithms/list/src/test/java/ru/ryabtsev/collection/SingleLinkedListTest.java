package ru.ryabtsev.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for SingleLinkedList class.
 */
public class SingleLinkedListTest {
    private List<Integer> integerList;

    @Test
    public void emptyListCreationTest() {
        resetList();
        Assert.assertEquals(0, integerList.size());
        Assert.assertTrue(integerList.isEmpty());
    }

    private void resetList() {
        integerList = new SingleLinkedList<>();
    }

    @Test
    public void addMethodTest() {
        resetList();
        integerList.add(1);
        Assert.assertEquals(1, integerList.size());
        integerList.add(3);
        Assert.assertEquals(2, integerList.size());
    }

    @Test
    public void containsMethodTest() {
        resetList();
        Assert.assertFalse(integerList.contains(0));
        integerList.add(1);
        integerList.add(3);
        Assert.assertTrue(integerList.contains(1));
        Assert.assertFalse(integerList.contains(2));
        Assert.assertTrue(integerList.contains(3));
    }

    @Test
    public void toArrayMethodTest() {
        resetList();
        integerList.add(1);
        integerList.add(3);
        Object[] array = integerList.toArray();
        Assert.assertEquals(2, array.length);
        Assert.assertEquals(1, ((Integer) array[0]).intValue());
        Assert.assertEquals(3, ((Integer) array[1]).intValue());
    }

    @Test
    public void toArrayInPlaceMethodTest() {
        resetList();
        Integer[] array = new Integer[2];
        integerList.add(1);
        array = integerList.toArray(array);
        Assert.assertEquals(2, array.length);
        Assert.assertEquals(1, array[0].intValue() );
        Assert.assertNull(array[1]);
        integerList.add(2);
        integerList.add(3);
        array = integerList.toArray(array);
        Assert.assertEquals(3, array.length);
        Assert.assertEquals(1, array[0].intValue() );
        Assert.assertEquals(2, array[1].intValue() );
        Assert.assertEquals(3, array[2].intValue() );
    }

    @Test
    public void removeMethodTest() {
        resetList();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        int size = integerList.size();
        Assert.assertTrue(integerList.remove(new Integer(2)));
        Assert.assertFalse(integerList.remove(new Integer(4)));
        Assert.assertEquals(size - 1, integerList.size());
        integerList.remove(new Integer(1));
        integerList.remove(new Integer(3));
        Assert.assertEquals(size - 3, integerList.size());
    }

    @Test
    public void addAllMethodTest() {
        resetList();
        sequentiallyFillList(3, 1);
        List<Integer> arrayList = new ArrayList<>();
        Assert.assertFalse(integerList.addAll(arrayList));
        for(int i = 0; i < integerList.size(); ++i) {
            arrayList.add(integerList.get(i));
        }
        integerList.addAll(arrayList);
        Assert.assertEquals(6, integerList.size());
        for(int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i % 3, integerList.get(i).intValue());
        }
    }

    @Test
    public void clearMethodTest() {
        resetList();
        sequentiallyFillList();
        integerList.clear();
        Assert.assertEquals(0, integerList.size() );
    }

    @Test
    public void getMethodTest() {
        resetList();
        sequentiallyFillList();
        for (int i = 9; i > -1; --i) {
            Assert.assertEquals( i, integerList.get(i).intValue() );
        }
    }

    private void sequentiallyFillList(int number, int step) {
        for (int i = 0; i < number; ++i) {
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
        for (int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i, integerList.set(i, i * 2).intValue());
            Assert.assertEquals(2 * i,  integerList.get(i).intValue());
        }
    }

    @Test
    public void addToPositionMethodTest() {
        resetList();
        integerList.add(0, 2);
        integerList.add(0, 0);
        integerList.add(1, 1);
        integerList.add( 3, 3);
        Assert.assertEquals(4, integerList.size());
        for (int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i, integerList.get(i).intValue());
        }
    }

    @Test
    public void removeFromPositionTest() {
        resetList();
        sequentiallyFillList(3, 1);
        Assert.assertEquals( 1, integerList.remove(1).intValue() );
        Assert.assertEquals( 0, integerList.remove(0).intValue() );
        Assert.assertEquals(1, integerList.size());
        Assert.assertEquals( 2, integerList.get(0).intValue() );
    }

    @Test
    public void toStringMethodTest() {
        resetList();
        sequentiallyFillList(3, 1);
        Assert.assertEquals("0 1 2", integerList.toString());
    }

    @Test
    public void indexOfMethodTest() {
        resetList();
        sequentiallyFillList( 3, 1);
        for(int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i, integerList.indexOf(i));
        }
    }

    @Test
    public void lastIndexOfMethodTest() {
        resetList();
        sequentiallyFillList( 3, 1);
        sequentiallyFillList( 3, 1);
        int halfSize = integerList.size() / 2;
        for(int i = 0; i < halfSize; ++i) {
            Assert.assertEquals(halfSize + i, integerList.lastIndexOf(i));
        }
    }
}


