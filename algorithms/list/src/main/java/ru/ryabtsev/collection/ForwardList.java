package ru.ryabtsev.collection;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

/**
 * My forward list implementation.
 * This list have not capacity restriction, so add*() methods from {@link java.util.Deque<T>} interface
 * implemented with corresponding offer*() methods.
 * @param <T> type of objects in the list.
 */
public class ForwardList<T> implements List<T>, Deque<T> {
    private transient int modificationsCount = 0;
    private transient int size;
    private transient Node<T> first;
    private transient Node<T> last;

    /**
     * Constructs empty single-linked list.
     */
    ForwardList() {
        this.size = 0;
        this.first = null;
        this.last = null;
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
        return new ForwardIterator();
    }

    @Override
    public Iterator<T> descendingIterator() {
        throw new UnsupportedOperationException("Forward list has not implementation for descending operator.");
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
        return offerLast(t);
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public void addFirst(T t) {
        offerFirst(t);
    }

    @Override
    public void addLast(T t) {
        offerLast(t);
    }

    @Override
    public boolean offerFirst(T t) {
        if( !isEmpty() ) {
            Node<T> newNode = new Node<>(t);
            newNode.next = first;
            first = newNode;
            ++this.size;
        }
        else {
            addFirstElement(t);
        }
        return true;
    }

    private void addFirstElement(T t) {
        first = new Node<>(t);
        last = first;
        ++this.size;
    }

    /**
     * Inserts the specified element at the end of this list.
     * @param t the element to add
     * @return always true because {@link ru.ryabtsev.collection.ForwardList<T>} hasn't capacity restrictions.
     */
    @Override
    public boolean offerLast(T t) {
        if( !isEmpty() ) {
            last.next = new Node<>(t);
            last = last.next;
            ++this.size;
        }
        else {
            addFirstElement(t);
        }
        return true;
    }

    @Override
    public T removeFirst() {
        if( isEmpty() ) {
            throw new NoSuchElementException();
        }
        return pollFirst();
    }

    @Override
    public T removeLast() {
        if( isEmpty() ) {
            throw new NoSuchElementException();
        }
        return pollLast();
    }

    @Override
    public T pollFirst() {
        if( first != null ) {
            T result = first.item;
            first = first.next;
            decreaseSizeByOne();
            return result;
        }
        return null;
    }

    private void decreaseSizeByOne() {
        size = --size > 0 ? size :  0;
    }

    @Override
    public T pollLast() {
        T result = (last != null) ? last.item : null;
        if(size > 1) {
            Node<T> previous = getNode(size - 2);
            previous.next = null;
            last = previous;
        }
        else {
            first = null;
            last = null;
        }
        decreaseSizeByOne();
        return result;
    }

    @Override
    public T getFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return peekFirst();
    }

    @Override
    public T getLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return peekLast();
    }

    @Override
    public T peekFirst() {
        return (isEmpty()) ? null : first.item;
    }

    @Override
    public T peekLast() {
        return (isEmpty()) ? null : last.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        int lastOccurrenceIndex = lastIndexOf(o);
        boolean result;
        if( result = isElementIndex(lastOccurrenceIndex) ) {
            remove(lastOccurrenceIndex);
        }
        return result;
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
        if(collection.size() == 0) {
            return false;
        }
        for( T element : collection ) {
            this.add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        if(collection.size() == 0) {
            return false;
        }
        int index = i;
        for( T element : collection ) {
            add(index++, element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if(collection.size() == 0) {
            return false;
        }
        boolean result = false;
        for( Object element: collection ) {
            result |= remove(element);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if(collection.size() == 0) {
            return false;
        }
        boolean result = false;
        for( int i = 0; i < size; ) {
            if( !collection.contains(this.get(i)) ) {
                remove(i);
                result |= true;
            }
            else {
                ++i;
            }
        }
        return result;
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
            //last = first;
        }
        ++this.size;
    }

    @Override
    public T remove(int i) {
        checkElementIndex(i);
        T result;
        if( i > 0 ) {
            Node<T> previousNode = getNode(i - 1);
            Node<T> removedNode = getNode(i);
            result = removedNode.item;
            previousNode.next = removedNode.next;
            if(i == size - 1) {
                last = previousNode;
            }
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
        return new ForwardListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return new ForwardListIterator(i);
    }

    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException();
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
            this(item, null);
        }

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private class ForwardIterator implements Iterator<T> {
        private ForwardList.Node<T> previous;
        private ForwardList.Node<T> current;


        ForwardIterator() {
            previous = null;
            current = ForwardList.this.first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T result = current.item;
            previous = current;
            current = current.next;
            return result;
        }

        @Override
        public void remove() {
            if(previous == null) {
                throw new IllegalStateException();
            }
            else {
                ForwardList.this.unlink(previous);
                previous = null;
            }
            --ForwardList.this.size;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> consumer) {
            while(hasNext()) {
                consumer.accept(next());
            }
        }
    }

    private void unlink(Node<T> node) {
        for(Node<T> current = first, previous = null; current != null; previous = current, current = current.next ) {
            if(current == node) {
                if(current != first) {
                    previous.next = current.next;
                }
                else {
                    first = current.next;
                }
            }
        }
    }

    private class ForwardListIterator implements ListIterator<T> {

        private ForwardList.Node<T> lastReturned;
        private ForwardList.Node<T> next;
        private int nextIndex;
        private int expectedModificationCount;

        ForwardListIterator() {
            lastReturned = null;
            next = ForwardList.this.first;
            nextIndex = 0;
            expectedModificationCount = ForwardList.this.modificationsCount;
        }

        ForwardListIterator(int i) {
            lastReturned = (i > 0) ? ForwardList.this.getNode(i - 1) : null;
            next = ForwardList.this.getNode(i);
            nextIndex = i;
            expectedModificationCount = ForwardList.this.modificationsCount;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < ForwardList.this.size();
        }

        @Override
        public T next() {
            if( !hasNext() ) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            ++nextIndex;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("Can't get previous element information from forward list iterator.");
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException("Can't get previous element from forward list iterator.");
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Can't get previous element from forward list iterator.");
        }

        @Override
        public void remove() {
            if(lastReturned == null) {
                throw new IllegalStateException();
            }
            else {
                ForwardList.this.unlink(lastReturned);
                lastReturned = null;
            }
            --ForwardList.this.size;
        }

        @Override
        public void set(T t) {
            next.item = t;
        }

        @Override
        public void add(T t) {
            this.lastReturned = null;
            ForwardList.this.add(nextIndex++, t);
        }

        final void checkForComodification() {
            if(ForwardList.this.modificationsCount != this.expectedModificationCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
