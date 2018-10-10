package ru.ryabtsev.se.events.service;

import ru.ryabtsev.se.events.event.AsyncEvent;
import ru.ryabtsev.se.events.event.SyncEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.awt.*;

/**
 * Event service class.
 */
@ApplicationScoped
public class EventService {
    @Inject
    private Event<AsyncEvent> asyncEvent;

    @Inject
    private Event<SyncEvent> syncEvent;

    /**
     * Runs service execution.
     */
    public void run() {
        asyncEvent.fireAsync( new AsyncEvent() );
        syncEvent.fire( new SyncEvent() );
    }
}
