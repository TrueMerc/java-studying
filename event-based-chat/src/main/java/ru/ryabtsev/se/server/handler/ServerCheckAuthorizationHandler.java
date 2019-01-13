package ru.ryabtsev.se.server.handler;

import lombok.SneakyThrows;
import ru.ryabtsev.se.server.event.ServerCheckAuthorizationEvent;
import ru.ryabtsev.se.server.service.ConnectionService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.enterprise.event.Event;

@ApplicationScoped
public class ServerCheckAuthorizationHandler {

    private static final long WAIT_FOR = 5000;

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerCheckAuthorizationEvent> serverCheckAuthorizationEvent;

    @SneakyThrows
    public void handle(@ObservesAsync ServerCheckAuthorizationEvent event) {
        connectionService.kickByTimeout();
        Thread.sleep( WAIT_FOR );
        serverCheckAuthorizationEvent.fireAsync( new ServerCheckAuthorizationEvent() );
    }
}
