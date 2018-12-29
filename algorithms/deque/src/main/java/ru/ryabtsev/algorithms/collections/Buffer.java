package ru.ryabtsev.algorithms.collections;

/**
 * Implements array based buffer.
 * @param <T> buffer element type.
 */
class Buffer<T> {

    protected Object[] buffer;

    /**
     * Returns buffer size.
     */
    protected int size() {
        return buffer.length;
    }

    /**
     * Returns buffer element by index.
     * @param index
     * @return element with given index.
     */
    protected T getElement(int index) {
        return (T)buffer[index];
    }

    /**
     * Sets buffer element by index.
     * @param element value.
     * @param index element index.
     */
    protected void setElement(T element, int index) {
        buffer[index] = element;
    }
}
