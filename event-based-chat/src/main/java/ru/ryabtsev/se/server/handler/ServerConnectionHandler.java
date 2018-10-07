package ru.ryabtsev.se.server.handler;

import lombok.SneakyThrows;
import ru.ryabtsev.se.server.ConnectionService;
import ru.ryabtsev.se.server.ConsoleServer;
import ru.ryabtsev.se.server.event.ServerConnectionEvent;
import ru.ryabtsev.se.server.event.ServerMessageEvent;
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
    private ConsoleServer server;

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerConnectionEvent> serverConnectionEvent;

    @Inject
    private Event<ServerMessageReadEvent> serverMessageEvent;

    @SneakyThrows
    public void handle( @Observes final ServerConnectionEvent event ) {
        System.out.println( "Server connection handler");
        final Socket socket = server.getServerSocket().accept();
        connectionService.add( socket );
        serverMessageEvent.fireAsync( new ServerMessageReadEvent( socket, "" ) );
        serverConnectionEvent.fire( new ServerConnectionEvent() );
    }
}
