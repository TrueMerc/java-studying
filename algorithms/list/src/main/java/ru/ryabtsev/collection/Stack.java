package ru.ryabtsev.collection;

/**
 * Simple queue interface.
 * @param <T> the elements type.
 */
public interface Stack<T> {
    /**
     * Pops the element out from the top of this stack.
     * @return the value of element from the top of this stack.
     */
    T pop();

    /**
     * Pushes specified element into the top of this stack.
     * @param element the element to push.
     */
    void push(T element);

    /**
     * Returns true if this stack contains no elements.
     * @return true if this stack contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns a string representation of the object.
     * @return string representation of the object.
     */
    String toString();
}
