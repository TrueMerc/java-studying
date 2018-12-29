package ru.ryabtsev.algorithms.collections;

/**
 * Implements simple 'stack' structure with fixed depth.
 */
public class Stack<T> {

    private static int EMPTY_INDEX = -1;

    private Object[] buffer;
    private int top;

    /**
     * Constructs new empty stack.
     */
    public Stack(int depth) {
        top = EMPTY_INDEX;
        buffer = new Object[depth];
    }

    /**
     * Returns true if stack is empty or false in the another case.
     */
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * Returns true if stack is full.
     */
    public boolean isFull() {
        return buffer.length - 1 == top;
    }

    /**
     * Pops top element of the stack.
     */
    public T pop() {
        return getElement(top--);
    }

    /**
     * Pushes new element into the top of the stack.
     */
    public void push(T element) {
        setElement(element, ++top);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack (top)-->(bottom):\n");
        for(int i = top; i > EMPTY_INDEX; --i) {
            stringBuilder.append(getElement(i)).append(' ');
        }
        return stringBuilder.toString();
    }

    /**
     * Returns buffer element by index.
     * @param index
     * @return element with given index.
     */
    private T getElement(int index) {
        return (T)buffer[index];
    }

    /**
     * Sets buffer element by index.
     * @param element value.
     * @param index element index.
     */
    private void setElement(T element, int index) {
        buffer[index] = element;
    }
}
