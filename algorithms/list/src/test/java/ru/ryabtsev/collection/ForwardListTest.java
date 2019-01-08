package ru.ryabtsev.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Unit test for ForwardList class.
 */
public class ForwardListTest extends ForwardListTestBase {

    @Test
    public void emptyListCreationTest() {
        resetList();
        Assert.assertEquals(0, integerList.size());
        Assert.assertTrue(integerList.isEmpty());
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
    public void pollFirstMethodTest() {
        resetList();
        int size = fillMainList();
        for(int i = 0; i < size; ++i) {
            Assert.assertEquals(i, integerList.pollFirst().intValue());
        }
        Assert.assertNull(integerList.poll());
    }

    @Test(expected = NoSuchElementException.class)
    public void  removeFirstMethodTest() {
        resetList();
        int size = fillMainList();
        for(int i = 0; i < size; ++i) {
            Assert.assertEquals(i, integerList.removeFirst().intValue());
        }
        Assert.assertTrue(integerList.isEmpty());
        integerList.remove();
    }

    @Test
    public void removeFirstOccurrenceMethodTest() {
        resetList();;
        int size = fillMainList(5, 1, 0 );
        integerList.set(0, 5);
        integerList.set(size - 1, 5);

        Assert.assertTrue(integerList.removeFirstOccurrence(new Integer(5)));
        Assert.assertEquals(size - 1, integerList.size());
        Assert.assertEquals( 1, integerList.getFirst().intValue() );
        Assert.assertEquals( 5, integerList.getLast().intValue() );
    }

    @Test
    public void removeLastOccurrenceMethodTest() {
        resetList();;
        int size = fillMainList(5, 1, 0 );
        integerList.set(0, 5);
        integerList.set(size - 1, 5);

        Assert.assertTrue(integerList.removeLastOccurrence(new Integer(5)));
        Assert.assertEquals(size - 1, integerList.size());
        Assert.assertEquals( 5, integerList.getFirst().intValue() );
        Assert.assertEquals( 1, integerList.getLast().intValue() );
    }

    @Test
    public void containsMethodTest() {
        resetList();
        Assert.assertFalse(integerList.contains(0));
        fillMainList(2, 1, 2);
        Assert.assertTrue(integerList.contains(1));
        Assert.assertFalse(integerList.contains(2));
        Assert.assertTrue(integerList.contains(3));
    }

    @Test
    public void toArrayMethodTest() {
        resetList();
        fillMainList(2, 1, 2);
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
        fillMainList(3, 1, 1);
        int size = integerList.size();
        Assert.assertTrue(integerList.remove(new Integer(2)));
        Assert.assertFalse(integerList.remove(new Integer(4)));
        Assert.assertEquals(size - 1, integerList.size());
        integerList.remove(new Integer(1));
        integerList.remove(new Integer(3));
        Assert.assertEquals(size - 3, integerList.size());
        Assert.assertFalse(integerList.remove(new Integer(4) ));
    }

    @Test
    public void containsAllMethodTest() {
        resetList();
        fillMainList();
        List<Integer> arrayList = new ArrayList<>();
        sequentiallyFillList(arrayList, 7, 8, -1);
        Assert.assertTrue(integerList.containsAll(arrayList));

        arrayList.add(integerList.size());
        Assert.assertFalse(integerList.containsAll(arrayList));
    }

    @Test
    public void addAllMethodTest() {
        resetList();
        fillMainList(3, 0, 1);
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
    public void addAllToPositionMethodTest() {
        resetList();
        List<Integer> arrayList = new ArrayList<>();
        sequentiallyFillList(arrayList, 5, 0, 1);

        // Insertion at empty list.
        integerList.addAll(0, arrayList);
        Assert.assertEquals(5, integerList.size() );
        for(int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i, integerList.get(i).intValue());
        }

        // Insertion at the end of the list.
        integerList.addAll( 5 , integerList);
        Assert.assertEquals( 10, integerList.size() );
        for(int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i % 5, integerList.get(i).intValue());
        }

        // Insertion at the middle of the list.
        integerList.addAll( 5, arrayList );
        Assert.assertEquals( 10, integerList.size() );
        for(int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i % 5, integerList.get(i).intValue());
        }
    }

    @Test
    public void removeAllMethodTest() {
        resetList();
        fillMainList(5, 1, 2);
        List<Integer> list1 = new ArrayList<>();
        sequentiallyFillList(list1, 2, 3, 6);
        Assert.assertTrue(integerList.removeAll(list1));
        Assert.assertFalse(integerList.removeAll(list1));
        Assert.assertEquals(3, integerList.size());
    }

    @Test
    public void retainAllMethodTest() {
        resetList();
        fillMainList(5, 1, 2);
        List<Integer> list1 = new ArrayList<>();
        sequentiallyFillList(list1, 2, 3, 6);
        List<Integer> list2 = new ArrayList<>();
        sequentiallyFillList(list2, 3, 0, 1);
        Assert.assertTrue(integerList.retainAll(list1));
        Assert.assertEquals(2, integerList.size());
        Assert.assertTrue(integerList.containsAll(list1));
        Assert.assertTrue(integerList.retainAll(list2));
        Assert.assertTrue(integerList.isEmpty());
    }

    @Test
    public void clearMethodTest() {
        resetList();
        fillMainList();
        integerList.clear();
        Assert.assertEquals(0, integerList.size() );
    }

    @Test
    public void getMethodTest() {
        resetList();
        fillMainList();
        for (int i = 9; i > -1; --i) {
            Assert.assertEquals( i, integerList.get(i).intValue() );
        }
    }

    @Test
    public void setMethodTest() {
        resetList();
        fillMainList();
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
        fillMainList(3, 0, 1);
        Assert.assertEquals( 1, integerList.remove(1).intValue() );
        Assert.assertEquals( 0, integerList.remove(0).intValue() );
        Assert.assertEquals(1, integerList.size());
        Assert.assertEquals( 2, integerList.get(0).intValue() );
    }

    @Test
    public void toStringMethodTest() {
        resetList();
        fillMainList(3, 0, 1);
        Assert.assertEquals("0 1 2", integerList.toString());
    }

    @Test
    public void indexOfMethodTest() {
        resetList();
        fillMainList( 3, 0, 1);
        for(int i = 0; i < integerList.size(); ++i) {
            Assert.assertEquals(i, integerList.indexOf(i));
        }
    }

    @Test
    public void lastIndexOfMethodTest() {
        resetList();
        fillMainList( 3, 0, 1);
        fillMainList( 3, 0, 1);
        int halfSize = integerList.size() / 2;
        for(int i = 0; i < halfSize; ++i) {
            Assert.assertEquals(halfSize + i, integerList.lastIndexOf(i));
        }
    }
}


