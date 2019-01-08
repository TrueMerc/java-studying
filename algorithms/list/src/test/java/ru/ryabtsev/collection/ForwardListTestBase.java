package ru.ryabtsev.collection;

import java.util.List;

/**
 * Base for ForwardList and its iterators tests.
 */
public class ForwardListTestBase {

    protected ForwardList<Integer> integerList;

    protected void resetList() {
        integerList = new ForwardList<>();
    }

    protected int fillMainList() {
        return fillMainList( 10, 0, 1);
    }

    protected int fillMainList(int size, int initial, int step) {
        return sequentiallyFillList(integerList, size, initial, step );
    }

    protected int sequentiallyFillList(List<Integer> list, int size, int initial, int step) {
        for (int i = 0; i < size; ++i) {
            list.add(initial + i * step);
        }
        return size;
    }
}
