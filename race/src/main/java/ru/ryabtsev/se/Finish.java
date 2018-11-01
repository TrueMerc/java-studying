package ru.ryabtsev.se;

import java.lang.invoke.SwitchPoint;
import java.util.concurrent.Semaphore;

public class Finish extends Stage {

    private final Semaphore semaphore;

    private static boolean HAS_WINNER;

    static {
        HAS_WINNER = false;
    }

    public Finish() {
        this.length = 0;
        this.description = "Финиш";
        this.semaphore = new Semaphore(1);
    }


    @Override
    public void go(Car c) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if( !HAS_WINNER ) {
            System.out.println( c.getName() + " ПОБЕДИЛ!!!");
            HAS_WINNER = true;
        }
        else {
            System.out.println( c.getName() + " финишировал.");
        }
        semaphore.release();
    }
}
