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
        this.first = new Node<>();
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        int index = 0;
        for( Node<T> current = first; current != null && index < size; current = current.next) {
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
            array[i++] = (T1)current.item;
        }

        if( array.length > this.size ) {
            array[i] = null;
        }

        return array;
    }

    @Override
    public boolean add(T t) {
        Node<T> last = getLast();
        if( last.item != null ) {
            last.next = new Node<>(t);
        }
        else {
            last.item = t;
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
        Object[] array = collection.toArray();
        boolean result = array.length > 0 ? true : false;
        for(int i = 0; i < array.length && result == true; ++i) {
            result &= contains(array[i]);
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Object[] array = collection.toArray();
        if(array.length == 0) {
            return false;
        }
        add((T)array[0]); // We are need to add the first element outside from for-loop because a list can be empty.
        Node<T> last = getLast();
        for(int i = 1; i < array.length; ++i) {
            last.next = new Node<>((T) array[i]);
            last = last.next;
            ++this.size;
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        Object[] array = collection.toArray();
        if(array.length == 0) {
            return false;
        }
        add(i, (T)array[0]); // We are need to add the first element outside from for-loop because a list can be empty.
        Node<T> previous = getNode(i);
        Node<T> next = getNode(i + 1);
        for(int j = 1; j < array.length; ++j) {
            previous.next = new Node<>((T) array[j]);
            previous = previous.next;
            ++this.size;
        }
        previous.next = next;
        return true;
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
        for(Node<T> current = first, previous = null; current != null; previous = current, current = current.next) {
            if( previous != null ) {
                previous.next = null;
            }
        }
        if( first != null ) {
            first.next = null;
            first = null;
        }
        this.size = 0;
    }

    @Override
    public T get(int i) {
        checkElementIndex(i);
        return getNode(i).item;
    }

    private void checkElementIndex(int i) throws IndexOutOfBoundsException {
        if( !this.isElementIndex(i) ) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isElementIndex(int i) {
        return (i >=0 &&  i < this.size);
    }

    private Node<T> getNode(int i) {
        if(!isElementIndex(i)) {
            return null;
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
        checkElementIndex(i);
        Node<T> node = getNode(i);
        T previous = node.item;
        node.item = t;
        return previous;
    }

    @Override
    public void add(int i, T t) {
        Node<T> newNode = new Node<>(t);
        Node<T> previousNode = getNode(i - 1);
        newNode.next = getNode(i);

        if(previousNode != null) {
            previousNode.next = newNode;
        }
        else {
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
        int result = -1, currentIndex = 0;
        for( Node<T> current = first; current != null; current = current.next, ++currentIndex ) {
            if(o.equals(current.item)) {
                result = currentIndex;
                break;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1, currentIndex = 0;
        for( Node<T> current = first; current != null; current = current.next, ++currentIndex ) {
            if(o.equals(current.item)) {
                result = currentIndex;
            }
        }
        return result;
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
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < this.size; ++i) {
            stringBuilder.append(get(i));
            if(i < this.size - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
            this.next = null;
        }

        Node() {
            this(null);
        }
    }
}
