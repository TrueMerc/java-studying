package ru.ryabtsev.collection;

import java.lang.reflect.Array;
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
        this.first = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.size;
    }

    @Override
    public boolean contains(Object o) {
       for( Node<T> current = first; current != null; current = current.next ) {
            if(o.equals(current.item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        int index = 0;
        for( Node<T> current = first; current != null; current = current.next) {
            array[index++] = current.item;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if( array.length < this.size ) {
            array = (T1[])(Array.newInstance(array.getClass().getComponentType(), this.size));
        }

        int i = 0;
        for(Node<T> current = first; current != null; current = current.next) {
            array[i++] = (T1)(current.item);
        }

        if( array.length > this.size ) {
            array[i] = null;
        }

        return array;
    }

    @Override
    public boolean add(T t) {
        if( !isEmpty() ) {
            Node<T> last = getLast();
            last.next = new Node(t);
        }
        else {
            first = new Node(t);
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
        for(Node<T> current = first, previous = null; current != null; previous = current, current = current.next) {
            if(o.equals(current.item)) {
                if( current != first ) {
                    previous.next = current.next;
                }
                else {
                    first = current.next;
                }
                --this.size;
                return true;
            }
        }
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
        return getNode(i).item;
    }

    private Node<T> getNode(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = first;
        for(int counter = 0; current != null; ++counter, current = current.next) {
            if(i == counter) {
                break;
            }
        }
        return current;
    }

    @Override
    public T set(int i, T t) {
        Node<T> node = getNode(i);
        T previous = node.item;
        node.item = t;
        return previous;
    }

    @Override
    public void add(int i, T t) {
        Node<T> newNode = new Node<>(t);
        if(i != 0) {
            Node<T> previousNode = getNode(i - 1);
            newNode.next = getNode(i);
            previousNode.next = newNode;
        }
        else {
            newNode.next = first;
            first = newNode;
        }
        ++this.size;
    }

    @Override
    public T remove(int i) {
        T result;
        if( i != 0 ) {
            Node<T> previousNode = getNode(i - 1);
            Node<T> removedNode = getNode(i);
            result = removedNode.item;
            previousNode.next = removedNode.next;
        }
        else {
            result = first.item;
            first = first.next;
        }
        --this.size;
        return result;
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
