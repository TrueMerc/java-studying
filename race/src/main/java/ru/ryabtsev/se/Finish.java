package ru.ryabtsev.se;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Finish extends Stage {

    private final ReentrantLock reentrantLock;

    private static boolean hasWinner;

    public Finish() {
        this.length = 0;
        this.description = "Финиш";
        this.reentrantLock = new ReentrantLock();
        this.hasWinner = false;
    }


    @Override
    public void go(Car c) {
        reentrantLock.lock();
        if( !hasWinner ) {
            System.out.println( c.getName() + " ПОБЕДИЛ!!!");
            hasWinner = true;
        }
        else {
            System.out.println( c.getName() + " финишировал.");
        }
        reentrantLock.unlock();
    }
}
