package ru.ryabtsev.algorithms;

import java.util.Map;

/**
 * My HashMap implementation.
 * @param <Key> map key type.
 * @param <Value> map value type.
 */
public class HashMap<Key, Value>  {

    /**
     * Implements map entry.
     * @param <Key> map key type.
     * @param <Value> map value type.
     */
    public static class Entry<Key, Value> {
        Key key;
        Value value;
        Entry next;

        /**
         * Constructs new entry with given key and value.
         * @param key entry key.
         * @param value entry value.
         */
        public Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Returns entry key.
         * @return entry key.
         */
        public Key getKey() {
            return key;
        }

        /**
         * Returns entry value.
         * @return entry value.
         */
        public Value getValue() {
            return value;
        }
    }

    private static final int INDEX_FOR_NULL_KEY = 0;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMAL_CAPACITY = Integer.MAX_VALUE / 2;

    private Entry[] table;
    private float loadFactor;
    int capacity;
    private int threshold;
    private int size;

    /**
     * Constructs default HashMap.
     */
    HashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs HashMap with given capacity.
     * @param capacity initial capacity.
     */
    HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs HashMap with given capacity and load factor.
     */
    HashMap(int capacity, float loadFactor) {
        this.table = new Entry[capacity];
        this.loadFactor = loadFactor;
        this.capacity = capacity;
        this.threshold = (int)loadFactor * capacity;
        this.size = 0;
    }

    /**
     * Returns hash map size.
     */
    int size() {
        return size;
    }

    /**
     * Returns true if hash map is empty or false in other case.
     */
    boolean isEmpty() {
        return 0 == this.size;
    }


    /**
     * Puts element in this hash map.
     * @param key element key.
     * @param value element value.
     * @return Old value associated with key, or null if there was no mapping for key.
     */
    Value put(Key key, Value value) {
        if( key == null) {
            Entry e = table[INDEX_FOR_NULL_KEY];
            if( e != null) {
                Value oldValue = (Value) e.value;
                e.value = value;
                return oldValue;
            }
            else {
                table[INDEX_FOR_NULL_KEY] = new Entry(key, value);
                ++size;
            }
            return null;
        }

        int keyHash = hash(key.hashCode());
        int index = indexFor(keyHash, table.length);

        return addEntry(table, index, key, value, keyHash);
    }

    static int hash(int hashCode) {
        hashCode^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    static int indexFor(int hash, int tableLength) {
        return hash & (tableLength - 1);
    }

    private Value addEntry(Entry[] table, int index, Key key, Value value, int keyHash) {
        Entry e = table[index];
        if( e != null) {
            Entry previous = null;
            while (e != null) {
                int entryHash = hash(e.key.hashCode());
                if ((entryHash == keyHash) && (e.key == key || key.equals(e.key))) {
                    Value oldValue = (Value) e.value;
                    e.value = value;
                    return oldValue;
                }
                previous = e;
                e = e.next;
            }
            previous.next = new Entry(key, value);
        }
        else {
            table[index] = new Entry(key, value);
        }
        ++size;
        return null;
    }

    /**
     * Returns value associated with given key, or false if there is no value associated with given key.
     * @param key element key.
     * @return value associated with given key, or false if there is no value associated with given key.
     */
    Value get(Key key) {
        if(key == null) {
            return (Value)table[INDEX_FOR_NULL_KEY].value;
        }

        int keyHash = hash(key.hashCode());
        int index = indexFor(keyHash, table.length);
        Entry e = table[index];

        if( e != null ) {
            while (e != null) {
                int entryHash = hash(e.key.hashCode());
                if ((entryHash == keyHash) && (e.key == key || key.equals(e.key))) {
                    return (Value)e.value;
                }
                e = e.next;
            }
        }

        return null;
    }

    /**
     * Changes capacity of this hash map to given value.
     * @param capacity new capacity of this hash map.
     */
    void resize(int capacity) {
        if(table.length >= MAXIMAL_CAPACITY) {
            threshold = Integer.MAX_VALUE;
        }

        Entry[] newTable = new Entry[capacity];
        transfer(newTable);
        table = newTable;
        this.capacity = capacity;
        threshold = (int)(capacity * loadFactor);
    }

    private void transfer(Entry[] newTable) {
        newTable[INDEX_FOR_NULL_KEY] = table[INDEX_FOR_NULL_KEY];

        for(int i = 1; i < table.length; ++i) {
            Entry e = table[i];
            if(e != null && e.value != null) {
                int keyHash = hash(e.key.hashCode());
                int index = indexFor(keyHash, newTable.length);

                addEntry(newTable, index, (Key)e.key, (Value)e.value, keyHash);
            }
        }
    }
}

