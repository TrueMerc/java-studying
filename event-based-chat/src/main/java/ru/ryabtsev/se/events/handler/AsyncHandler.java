package ru.ryabtsev.se.events.handler;

import ru.ryabtsev.se.events.event.SyncEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;


/**
 * Asynchronous events handler class.
 */
@ApplicationScoped
public class AsyncHandler {

    /**
     * Handles event.
     * @param event - current event.
     */
    void handle(@ObservesAsync SyncEvent event) {
        System.out.println( "AsyncHandler: " + Thread.currentThread().getId() );
    }
}
