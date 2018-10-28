package ru.ryabtsev.se;


import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NumberValue {
    long value;
}

class TaskFirst extends Thread {
    private final NumberValue numberValue;

    public TaskFirst( final  NumberValue numberValue ) {
        this.numberValue = numberValue;
    }

    @Override
    public void run() {
        System.out.println(numberValue.value);
    }
}

class TaskSecond extends Thread {
    private final NumberValue numberValue;

    public TaskSecond( final NumberValue numberValue ) {
        this.numberValue = numberValue;
    }

    @Override
    public void run() {
        numberValue.value = 2;
    }
}

/**
 * Executor service task (from Java Core Professional - Homework 4).
 */
public class ExecutorServiceTaskApplication
{
    public static void testCall() {
        final NumberValue numberValue = new NumberValue(); // numberValue.value == 0
        final ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit( new TaskSecond( numberValue ) );
        executor.submit( new TaskFirst( numberValue ) );
        //System.out.println(numberValue.value);
        executor.shutdown();
    }

    public static void main( String[] args )
    {
        for(int i = 0; i < 100; ++i) {
            testCall();
        }
    }
}
