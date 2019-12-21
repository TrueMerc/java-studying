package ru.ryabtsev.collection;

/**
 * Provides interface for the light version of List<T> interface.
 * @param <T> type of elements of this list.
 */
public interface LightList<T> {

    int size();

    boolean isEmpty();

    boolean add(T element);

    void add(int position, T element);

    boolean remove(T element);

    T get(int position);

    T set(int position, T element);

    int indexOf(T element);
}
