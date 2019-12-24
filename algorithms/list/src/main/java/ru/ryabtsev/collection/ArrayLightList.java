package ru.ryabtsev.collection;

public class ArrayLightList<T> implements LightList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final float CAPACITY_FACTOR = 1.5f;
    private transient Object[] elements;
    private transient int size;

    ArrayLightList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    ArrayLightList(int initialCapacity) {
        elements = new Object[initialCapacity];
        size = 0;
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
        if(size >= elements.length) {
            reallocateElements();
        }
        
        elements[size++] = element;
        
        return true;
    }
    
    void reallocateElements() {
        Object[] newElements = new Object[(int)(elements.length * CAPACITY_FACTOR)];
        for(int i = 0; i < elements.length; ++i) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public void add(int index, T element) {
        checkAddElementIndex(index);
        if(size >= elements.length) {
            reallocateElements();
        }

        if(index == size) {
            add(element);
        }
        else {
            for(int i = size; i > index; --i) {
                elements[i] = elements[i-1];
            }
            elements[index] = element;
        }
    }

    private void checkAddElementIndex(int index) throws IndexOutOfBoundsException {
        if(0 > index || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(T element) {
        for(int i = 0; i < size; ++i) {
            if(element.equals((T)elements[i])) {
                for(int j = i; j < size - 1; ++j) {
                    elements[j] = elements[j + 1];
                }
                --size;
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return (T)elements[index];
    }

    private void checkElementIndex(int index) throws IndexOutOfBoundsException {
        if(!isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isElementIndex(int index) {
        return 0 <= index && index <= size;
    }

    @Override
    public T set(int index, T element) {
        checkElementIndex(index);
        T previous = (T)elements[index];
        elements[index] = element;
        return previous;
    }

    @Override
    public int indexOf(T element) {
        for(int i = 0; i < size; ++i) {
            if(element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }
}
