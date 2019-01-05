package ru.ryabtsev.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * My single-linked list implementation.
 * @param <T> type of objects in the list.
 */
public class SingleLinkedList<T> implements List<T> {
    private transient int size;
    private transient Node<T> first;

    /**
     * Constructs empty single-linked list.
     */
    SingleLinkedList() {
        this.size = 0;
        this.first = new Node(null);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (0 == size);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if(size != 0) {
            Node<T> last = getLast();
            Node<T> newNode = new Node(t);
            last.next = newNode;
        }
        else {
            first.item = t;
        }
        ++this.size;
        return true;
    }

    private Node<T> getLast() {
        Node<T> node = first;
        while( node.next != null ) {
            node = node.next;
        }
        return node;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public T set(int i, T t) {
        return null;
    }

    @Override
    public void add(int i, T t) {

    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        return null;
    }

    @Override
    public String toString() {
        return new String();
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
            this.next = null;
        }
    }
}
