package ru.ryabtsev.collection;

/**
 * Simple queue interface.
 * @param <T> the elements type.
 */
public interface Queue<T> {
    /**
     * Returns value of the first element of queue, then removes this element.
     * @return the value of the first element of queue.
     */
    T poll();

    /**
     * Adds specified element into the end of this queue.
     * @param element the element to push.
     */
    void add(T element);

    /**
     * Returns true if this queue contains no elements.
     * @return true if this queue contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns a string representation of the object.
     * @return string representation of the object.
     */
    String toString();
}
