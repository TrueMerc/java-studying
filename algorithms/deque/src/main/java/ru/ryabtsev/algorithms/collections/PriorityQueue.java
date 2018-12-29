package ru.ryabtsev.algorithms.collections;

/**
 * Implements simple 'queue' structure with fixed depth.
 * @param <T> queue element type.
 */
public class PriorityQueue<T extends Comparable> extends Buffer<T> {

    private int elements;

    /**
     * Constructs new empty priority queue.
     */
    public PriorityQueue(int size) {
        super(size);
//        front = INITIAL_FRONT;
//        rear = INITIAL_REAR;
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
     * Inserts new element into a queue.
     */
    public void insert(T element){
        int i;
        if(0 == elements) {
            setElement(element, elements++);
        }
        else{
            for(i = elements - 1; i >=0; --i){
                if( element.compareTo(getElement(i)) > 0 ) {
                    setElement(getElement(i), i + 1);
                }
                else {
                    break;
                }
            }
            setElement(element, i+1);
            elements++;
        }
    }
    public T remove(){
        return getElement(--elements);
    }

    /**
     * Peeks element in the front of a queue.
     */
    public T peek(){
        return getElement(elements - 1 );
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Priority queue [maxSize: " + maxSize() + "] (front) <-- (back):\n");
        for( int i = 0; i < elements; ++i ) {
            stringBuilder.append(getElement(i)).append(' ');
        }
        if(isEmpty()) {
            stringBuilder.append("(empty)");
        }
        return stringBuilder.toString();
    }
}

