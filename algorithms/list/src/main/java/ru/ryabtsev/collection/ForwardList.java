package ru.ryabtsev.collection;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

/**
 * My forward list implementation.
 * This list have not capacity restriction, so add* methods from @see Deque<T> interface
 * implemented with corresponding offer* methods.
 * @param <T> type of objects in the list.
 */
public class ForwardList<T> implements List<T>, Deque<T> {
    private transient int modificationsCount = 0;
    private transient int size;
    private transient Node<T> first;
    //private transient Node<T>

    /**
     * Constructs empty single-linked list.
     */
    ForwardList() {
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
        return new ForwardIterator();
    }

    @Override
    public Iterator<T> descendingIterator() {
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
        return offerLast(t);
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void push(T t) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public void addFirst(T t) {

    }

    @Override
    public void addLast(T t) {

    }

    @Override
    public boolean offerFirst(T t) {
        return false;
    }

    @Override
    public boolean offerLast(T t) {
        Node<T> last = getLastNode();
        if( last.item != null ) {
            last.next = new Node<>(t);
        }
        else {
            last.item = t;
        }
        ++this.size;
        return true;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T pollFirst() {
        return null;
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public T getFirst() {
        return first.item;
    }

    @Override
    public T getLast() {
        return getLastNode().item;
    }

    private Node<T> getLastNode() {
        Node<T> node = first;
        while( node.next != null ) {
            node = node.next;
        }
        return node;
    }

    @Override
    public T peekFirst() {
        return null;
    }

    @Override
    public T peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
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
        Node<T> last = getLastNode();
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
        Object[] array = collection.toArray();
        if(array.length == 0) {
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
            this.item = item;
            this.next = null;
        }

        Node() {
            this(null);
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

//    private Node<T> previous(Node<T> node) {
//        for(Node<T> current = first, previous = null; current != null; previous = current, current = current.next ) {
//            if(node == current) {
//                return previous;
//            }
//        }
//        return null;
//    }
//
//    private int getPreviousIndex(Node<T> node) {
//        int index = -1;
//        for(Node<T> current = first; current != null; current = current.next, ++index) {
//            if(node == current) {
//                break;
//            }
//        }
//        return index;
//    }
//
//    private int getNextIndex(Node<T> node) {
//        int index = 0;
//        for(Node<T> current = first; current != null; current = current.next, ++index) {
//            if(node.next == current) {
//                break;
//            }
//        }
//        return index;
//    }
}
