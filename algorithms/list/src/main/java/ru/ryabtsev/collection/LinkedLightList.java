package ru.ryabtsev.collection;

public class LinkedLightList<T> implements LightList<T> {
    private transient int size;
    private transient Node<T> first;
    private transient Node<T> last;

    LinkedLightList() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public boolean add(T element) {
        if(!isEmpty()) {
            last.next = new Node<>(element, last);
        }
        else {
            addFirstElement(element);
        }
        return true;
    }

    private boolean addFirstElement(T element) {
        first = new Node<>(element);
        last = first;
        size = 1;
        return true;
    }

    @Override
    public void add(int index, T element) {
        checkAddElementIndex(index);
        Node<T> previousNode = getNode(index - 1);
        Node<T> nextNode = getNode(index);
        Node<T> newNode = new Node<>(element, previousNode, nextNode);

        if(previousNode != null) {
            previousNode.next = newNode;
        }
        else {
            first = newNode;
        }

        if(nextNode != null) {
            nextNode.previous = newNode;
        }
        else {
            last = newNode;
        }

        ++this.size;
    }

    private void checkAddElementIndex(int index) throws IndexOutOfBoundsException {
        if(0 > index || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(T element) {
        for(Node<T> current = first; current != null; current = current.next) {
            if(element.equals(current.item)) {
                Node<T> previous = current.previous;
                Node<T> next = current.next;

                if(previous != null) {
                    previous.next = next;
                }
                else {
                    first = next;
                }

                if(next != null) {
                    next.previous = previous;
                }
                else {
                    last = previous;
                }
                --this.size;
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).item;
    }

    private Node<T> getNode(int index) {
        if(!isElementIndex(index)) {
            return null;
        }
        Node<T> current = first;
        for(int counter = 0; current != null; current = current.next, ++counter) {
            if(counter == index) {
                break;
            }
        }
        return current;
    }

    private void checkElementIndex(int index) throws IndexOutOfBoundsException {
        if(!isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isElementIndex(int index) {
        return 0 <= index && index < size;
    }

    @Override
    public T set(int index, T element) {
        checkElementIndex(index);
        Node<T> node = getNode(index);
        T previous = node.item;
        node.item = element;
        return previous;
    }

    @Override
    public int indexOf(T element) {
        int currentIndex = 0;
        for(Node<T> current = first; current != null; current = current.next, ++currentIndex ) {
            if(element.equals(current.item)) {
                return currentIndex;
            }
        }
        return -1;
    }

    private static class Node<T> {
        T item;
        LinkedLightList.Node<T> previous;
        LinkedLightList.Node<T> next;

        Node(T item) {
            this(item, null, null);
        }

        Node(T item, LinkedLightList.Node<T> previous) {
            this(item, previous, null);
        }

        Node(T item, LinkedLightList.Node<T> previous, LinkedLightList.Node<T> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }
}
