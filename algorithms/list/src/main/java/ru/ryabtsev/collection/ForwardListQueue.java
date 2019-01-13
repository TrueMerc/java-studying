package ru.ryabtsev.collection;

/**
 * Queue implementation based on the forward list class.
 * @param <T> the type of elements.
 */
public class ForwardListQueue<T> implements Queue<T> {

    private ForwardList<T> list;

    /**
     * Constructs new queue based on the forward list.
     */
    ForwardListQueue() {
        list = new ForwardList<>();
    }

    /**
     * Returns value of the first element of queue, then removes this element.
     *
     * @return the value of the first element of queue.
     */
    @Override
    public T poll() {
        return list.pollFirst();
    }

    /**
     * Adds specified element into the end of this queue.
     *
     * @param element the element to push.
     */
    @Override
    public void add(T element) {
        list.addLast(element);
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns a string representation of the object.
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return list.toString();
    }

}