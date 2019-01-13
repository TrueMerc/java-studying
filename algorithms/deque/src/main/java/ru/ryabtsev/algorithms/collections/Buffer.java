package ru.ryabtsev.algorithms.collections;

/**
 * Implements array based buffer.
 * @param <T> buffer element type.
 */
class Buffer<T> {

    protected Object[] buffer;

    /**
     * Constructs buffer.
     */
    protected Buffer(int size) {
        buffer = new Object[size];
    }

    /**
     * Returns buffer length.
     */
    protected int length() {
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
