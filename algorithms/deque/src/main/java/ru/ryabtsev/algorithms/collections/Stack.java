package ru.ryabtsev.algorithms.collections;

/**
 * Implements simple 'stack' structure with fixed depth.
 * @param <T> stack element type.
 */
public class Stack<T> extends Buffer<T> {

    private static int EMPTY_INDEX = -1;

    private int top;

    /**
     * Constructs new empty stack.
     */
    public Stack(int depth) {
        top = EMPTY_INDEX;
        buffer = new Object[depth];
    }

    /**
     * Returns stack depth.
     */
    public int depth() {
        return super.size();
    }

    /**
     * Returns true if stack is empty or false in the another case.
     */
    public boolean isEmpty() {
        return top == EMPTY_INDEX;
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
        stringBuilder.append("Stack [depth:" + depth() + "] (top)-->(bottom):\n");
        for(int i = top; i > EMPTY_INDEX; --i) {
            stringBuilder.append(getElement(i)).append(' ');
        }
        if( isEmpty() ) {
            stringBuilder.append("(empty)");
        }
        return stringBuilder.toString();
    }
}
