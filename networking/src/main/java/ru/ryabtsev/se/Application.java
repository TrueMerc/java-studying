package ru.ryabtsev.se;

import java.util.concurrent.ExecutorService;

public interface Application extends Runnable {
    void run();
}
