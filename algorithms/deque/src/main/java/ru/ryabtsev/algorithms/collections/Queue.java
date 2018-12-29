package ru.ryabtsev.algorithms.collections;

/**
 * Implements simple 'queue' structure with fixed depth.
 * @param <T> queue element type.
 */
public class Queue<T> extends Buffer<T> {

    private static final int INITIAL_FRONT = 0;
    private static final int INITIAL_REAR = -1;

    private int front;
    private int rear;
    private int elements;

    /**
     * Constructs new empty queue.
     */
    public Queue(int size) {
        super(size);
        front = INITIAL_FRONT;
        rear = INITIAL_REAR;
        elements = 0;
    }

    /**
     * Return queue maximal length.
     */
    public int maxSize() {
        return super.length();
    }

    /**
     * Returns queue current length.
     */
    public int size() {
        return elements;
    }

    /**
     * Returns true if stack is empty or false in the another case.
     */
    public boolean isEmpty() {
        return 0 == elements;
    }

    /**
     * Returns true if stack is full.
     */
    public boolean isFull() {
        return this.size() == this.maxSize();
    }

    /**
     * Inserts new element into the end of a queue.
     */
    public void insert(T element) {
        rear = (rear != this.maxSize()) ? rear : INITIAL_REAR;
        setElement(element, ++rear);
        ++elements;
    }

    /**
     * Removes element from the front of a queue.
     */
    public T remove() {
        final T result = getElement(front++);
        front = (front != this.maxSize()) ? front : INITIAL_FRONT;
        --elements;
        return result;
    }

    /**
     * Peeks element in the front of a queue.
     */
    public T peek() {
        return getElement(front);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue [maxSize " + maxSize() + "] (front) <-- (back):\n");
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
