package ru.ryabtsev.se.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.server.event.*;

import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

import static ru.ryabtsev.se.packets.PacketType.*;

public class ServerMessageInputHandler {

    @Inject
    private Event<ServerMessageInputEvent> serverMessageInputEvent;

    @Inject
    private Event<ServerPingEvent> serverPingEvent;

    @Inject
    private Event<ServerRegistryEvent> serverRegistryEvent;

    @Inject
    private Event<ServerLoginEvent> serverLoginEvent;

    @Inject
    private Event<ServerLogoutEvent> serverLogoutEvent;

    @Inject Event<ServerBroadcastEvent> serverBroadcastEvent;


    @SneakyThrows
    public void handle( @ObservesAsync final ServerMessageInputEvent event ) {
        System.out.println( "Server message input handler");
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessage();
        System.out.println(message);
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Packet packet = objectMapper.readValue( message, Packet.class );

        switch( packet.getType() ) {
            case PING:
                serverPingEvent.fireAsync( new ServerPingEvent(socket, message) );
                break;

            case REGISTRY:
                serverRegistryEvent.fireAsync( new ServerRegistryEvent( socket, message) );
                break;

            case LOGIN:
                serverLoginEvent.fireAsync( new ServerLoginEvent( socket, message ) );
                break;

            case LOGOUT:
                serverLogoutEvent.fireAsync( new ServerLogoutEvent( socket, message ) );
                break;

            case BROADCAST:
                serverBroadcastEvent.fireAsync( new ServerBroadcastEvent( socket, message ) );
                break;
        }

        serverMessageInputEvent.fire( new ServerMessageInputEvent( socket, message ) );
    }
}