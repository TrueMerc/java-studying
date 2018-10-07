package ru.ryabtsev.se.application;

import javax.enterprise.context.ApplicationScoped;

/**
 * Basic interface for application classes.
 */
public interface Application extends Runnable {
    void run();
}
