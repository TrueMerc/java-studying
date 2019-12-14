package ru.ryabtsev.se.events.handler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import ru.ryabtsev.se.events.event.SyncEvent;


/**
 * Synchronous events handler class.
 */
@ApplicationScoped
public class SyncHandler {

    /**
     * Handles event.
     * @param event - current event.
     */
    void handle(@Observes SyncEvent event) {
        System.out.println( "SyncHandler: " + Thread.currentThread().getId() );
    }
}
