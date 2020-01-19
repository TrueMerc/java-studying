package ru.ryabtsev;

import lombok.SneakyThrows;

/**
 * Implements synchronized "Ping - Pong" messages print.
 */
public class SynchronizedPrint {

    private static final String PING_MESSAGE = "Ping";
    private static final String PONG_MESSAGE = "Pong";

    private String nextMessage = PING_MESSAGE;

    @SneakyThrows
    public synchronized void print(String message) {
        while(!message.equals(nextMessage)) {
            wait();
        }

        System.out.println(message);
        nextMessage = getNextMessage(message);
        notify();
    }

    private synchronized String getNextMessage(String message) {
        if(message.equals(PING_MESSAGE)) {
            return PONG_MESSAGE;
        }
        else {
            return PING_MESSAGE;
        }
    }
}
