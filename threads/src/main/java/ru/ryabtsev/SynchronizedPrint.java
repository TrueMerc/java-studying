package ru.ryabtsev;

/**
 * Implements synchronized "Ping - Pong" messages print.
 */
public class SynchronizedPrint {

    private static final String PING_MESSAGE = "Ping";
    private static final String PONG_MESSAGE = "Pong";

    private String nextMessage = PING_MESSAGE;

    public synchronized boolean print(String message) {
        if(message.equals(nextMessage)) {
            System.out.println(message);
            if(message.equals(PING_MESSAGE)) {
                nextMessage = PONG_MESSAGE;
            }
            else {
                nextMessage = PING_MESSAGE;
            }
            return true;
        }
        return false;
    }
}
