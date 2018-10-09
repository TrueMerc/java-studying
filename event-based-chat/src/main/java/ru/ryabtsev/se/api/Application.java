package ru.ryabtsev.se.api;

import javax.enterprise.context.ApplicationScoped;

/**
 * Basic interface for application classes.
 */
public interface Application extends Runnable {
    void run();
}
