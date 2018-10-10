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

    private static final long WAITING_FOR = 5000;

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerCheckAuthorizationEvent> serverCheckAuthorizationEvent;

    @SneakyThrows
    public void handle(@ObservesAsync ServerCheckAuthorizationEvent event) {
        System.out.println("Server check authorization handler.");
        connectionService.kickByTimeout();
        System.out.println("Waiting started.");
        //Thread.currentThread().wait( WAITING_FOR );
        Thread.sleep( WAITING_FOR );
        System.out.println("Waiting finished.");
        serverCheckAuthorizationEvent.fireAsync( new ServerCheckAuthorizationEvent() );
    }
}
