package ru.ryabtsev;

public class PongMessage implements Runnable {

    private static final int MESSAGES_NUMBER = 10;
    private static final String MESSAGE = "Pong";
    private SynchronizedPrint print;

    public PongMessage(SynchronizedPrint print) {
        this.print = print;
    }

    @Override
    public void run() {
        for(int i = 0; i < MESSAGES_NUMBER; ++i) {
            print.print(MESSAGE);
        }
    }
}
