package ru.ryabtsev.algorithms.collections;

/**
 * Implements simple 'double ended queue' structure with fixed depth.
 * @param <T> deque element type.
 */
public class Deque<T> extends Buffer<T> {

    private static final int INITIAL_FRONT = 0;
    private static final int INITIAL_REAR = -1;

    private int front;
    private int rear;
    private int elements;

    public Deque(int size) {
        super(size);
        elements = 0;
        front = INITIAL_FRONT;
        rear = INITIAL_REAR;
        elements = 0;
    }

    /**
     * Return deque maximal length.
     */
    public int maxSize() {
        return super.length();
    }

    /**
     * Returns deque current length.
     */
    public int size() {
        return elements;
    }

    /**
     * Returns true if deque is empty or false in the another case.
     */
    public boolean isEmpty() {
        return 0 == elements;
    }

    /**
     * Returns true if deque is full.
     */
    public boolean isFull() {
        return this.size() == this.maxSize();
    }

    /**
     * Inserts new element into the end of a deque.
     */
    public void insertRight(T element) {
        rear = (rear != this.maxSize()) ? rear : INITIAL_REAR;
        setElement(element, ++rear);
        ++elements;
    }

    /**
     * Inserts new element into the front of a deque.
     */
    public void insertLeft(T element) {
        front = (front != INITIAL_FRONT) ? front : this.maxSize();
        setElement(element, --front);
        ++elements;
    }

    /**
     * Removes element from the front of a deque.
     */
    public T removeLeft() {
        final T result = getElement(front++);
        front = (front != this.maxSize()) ? front : INITIAL_FRONT;
        --elements;
        return result;
    }

    /**
     * Removes element from the end of a deque.
     */
    public T removeRight() {
        final T result = getElement(rear--);
        rear = (front != INITIAL_REAR) ? rear : this.maxSize() - 1;
        --elements;
        return result;
    }

    /**
     * Peeks element in the front of a deque.
     */
    public T peekLeft() {
        return getElement(front);
    }

    /**
     * Peeks element in the end of a deque.
     */
    public T peekRight() {
        return getElement(rear);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Deque [maxSize " + maxSize() + "] (front) <-- (back):\n");
        for( int i = front, j = 0; j < elements; ++i, ++j ) {
            i = ( i != this.maxSize() ) ? i : INITIAL_FRONT;
            stringBuilder.append(getElement(i)).append(' ');
        }
        if(isEmpty()) {
            stringBuilder.append("(empty)");
        }
        return stringBuilder.toString();
    }
}
