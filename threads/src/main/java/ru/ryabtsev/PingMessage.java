package ru.ryabtsev;

public class PingMessage implements Runnable {

    private static final int MESSAGES_NUMBER = 10;
    private static final String MESSAGE = "Ping";
    private SynchronizedPrint print;

    public PingMessage(SynchronizedPrint print) {
        this.print = print;
    }

    @Override
    public void run() {
        for(int i = 0; i < MESSAGES_NUMBER; ++i) {
            print.print(MESSAGE);
        }
    }
}
