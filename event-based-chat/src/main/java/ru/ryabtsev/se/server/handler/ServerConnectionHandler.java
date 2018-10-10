package ru.ryabtsev.se.server.handler;

import lombok.SneakyThrows;
import ru.ryabtsev.se.server.event.ServerCheckAuthorizationEvent;
import ru.ryabtsev.se.server.service.ConnectionServiceBean;
import ru.ryabtsev.se.server.Server;
import ru.ryabtsev.se.server.event.ServerConnectionEvent;
import ru.ryabtsev.se.server.event.ServerMessageReadEvent;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.net.Socket;

/**
 * Handles server connection event.
 */
@ApplicationScoped
public class ServerConnectionHandler {

    @Inject
    private Server server;

    @Inject
    private ConnectionServiceBean connectionService;

    @Inject
    private Event<ServerConnectionEvent> serverConnectionEvent;

    @Inject
    private Event<ServerMessageReadEvent> serverMessageReadEvent;

    @SneakyThrows
    public void handle( @Observes final ServerConnectionEvent event ) {
        System.out.println( "Server connection handler");
        final Socket socket = server.getServerSocket().accept();
        connectionService.add( socket );
        serverMessageReadEvent.fireAsync( new ServerMessageReadEvent( socket ) );
        serverConnectionEvent.fire( new ServerConnectionEvent() );
    }
}
