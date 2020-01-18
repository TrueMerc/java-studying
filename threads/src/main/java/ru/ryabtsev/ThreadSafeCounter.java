package ru.ryabtsev;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implements thread-safe counter.
 */
public class ThreadSafeCounter {

    Lock lock = new ReentrantLock();
    private int value = 0;

    ThreadSafeCounter(int value) {
        this.value = value;
    }

    int increase(String caller) {
        lock.lock();
        try {
            ++value;
            System.out.println(caller + ":" + value);
        }
        finally {
            lock.unlock();
        }
        return value;
    }
}
