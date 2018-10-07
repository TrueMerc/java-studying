package ru.ryabtsev.se.server.handler;


import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.server.ConnectionService;
import ru.ryabtsev.se.server.event.ServerConnectionEvent;
import ru.ryabtsev.se.server.event.ServerMessageInputEvent;
import ru.ryabtsev.se.server.event.ServerMessageReadEvent;

import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Handles messages at server input.
*/
public class ServerMessageReadHandler {

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerMessageReadEvent> serverMessageReadEvent;

    @Inject
    private Event<ServerMessageInputEvent> serverMessageInputEvent;


    public void handle( @ObservesAsync final ServerMessageReadEvent event ) {
        System.out.println( "Server message read handler");
        final Socket socket = event.getSocket();
        try {
            @NotNull final InputStream inputStream = socket.getInputStream();
            @NotNull final DataInputStream in = new DataInputStream( inputStream );
            @NotNull final String message = in.readUTF();
            serverMessageReadEvent.fireAsync( new ServerMessageReadEvent( socket, "" ) );
            serverMessageInputEvent.fire( new ServerMessageInputEvent( socket, "" ) );
        }
        catch (@NotNull final Exception exception) {
            connectionService.remove( socket );
        }
    }
}
