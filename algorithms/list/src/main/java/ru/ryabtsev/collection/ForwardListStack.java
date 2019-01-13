package ru.ryabtsev.collection;

/**
 * Stack implementation based on the forward list class.
 * @param <T> the type of elements.
 */
public class ForwardListStack<T> implements Stack<T> {

    private final ForwardList<T> list;

    public ForwardListStack() {
        list = new ForwardList<>();
    }

    @Override
    public T pop() {
        return list.removeLast();
    }

    @Override
    public void push(T element) {
        list.addLast(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() { return list.toString(); }
}