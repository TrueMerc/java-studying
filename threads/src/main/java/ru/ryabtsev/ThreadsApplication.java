package ru.ryabtsev;

import lombok.SneakyThrows;

/**
 * Implements solution of tasks from the third homework of "Interview preparation course".
 */
public class ThreadsApplication {

    private static final int SECOND_TASK_INCREASE_CALLS_COUNT = 5;
    private static final int SECOND_TASK_THREADS_NUMBER = 4;
    private static final int SLEEP_FOR_MILLISECONDS = 5;

    public static void main( String[] args ) {
        executeFirstTask();
        executeSecondTask();
    }

    @SneakyThrows
    private static final void executeFirstTask() {
        System.out.println("First task execution started.");
        SynchronizedPrint synchronizedPrint = new SynchronizedPrint();

        PingMessage pingMessage = new PingMessage(synchronizedPrint);
        PongMessage pongMessage = new PongMessage(synchronizedPrint);

        Thread ping = new Thread( pingMessage );
        Thread pong = new Thread( pongMessage );

        ping.start();
        pong.start();

        ping.join();
        pong.join();

        System.out.println("First task execution finished.");
    }

    @SneakyThrows
    private static final void executeSecondTask() {
        System.out.println("Second task execution started.");
        ThreadSafeCounter counter = new ThreadSafeCounter(5);

        Thread[] threads = new Thread[SECOND_TASK_THREADS_NUMBER];

        for(int i = 0; i < SECOND_TASK_THREADS_NUMBER; ++i) {
            final int number = i;
            threads[i] = new Thread(()->{
                        secondTaskThreadFunction(counter, number, number * SLEEP_FOR_MILLISECONDS);
                    });
            threads[i].start();
        }

        for(int i = 0; i < SECOND_TASK_THREADS_NUMBER; ++i) {
            threads[i].join();
        }

        System.out.println("Second work execution finished.");
    }

    @SneakyThrows
    private static void secondTaskThreadFunction(ThreadSafeCounter counter, int threadNumber, int sleepForMilliseconds) {
        for(int i = 0; i < SECOND_TASK_INCREASE_CALLS_COUNT; ++i) {
            counter.increase("Thread " + threadNumber);
            Thread.sleep(sleepForMilliseconds);
        }
    }
}
