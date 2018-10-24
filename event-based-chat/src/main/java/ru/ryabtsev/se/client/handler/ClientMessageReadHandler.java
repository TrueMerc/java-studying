package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.client.Client;
import ru.ryabtsev.se.client.event.ClientMessageReadEvent;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.server.event.ServerBroadcastEvent;
import ru.ryabtsev.se.server.event.ServerLogoutEvent;
import ru.ryabtsev.se.server.event.ServerSetNicknameEvent;
import ru.ryabtsev.se.server.event.ServerUnicastEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.IOException;

import static ru.ryabtsev.se.packets.PacketType.*;
import static ru.ryabtsev.se.packets.PacketType.SETNICKNAME_REQUEST;
import static ru.ryabtsev.se.packets.PacketType.UNICAST_REQUEST;

@ApplicationScoped
public class ClientMessageReadHandler {

    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    public void handle(@ObservesAsync ClientMessageReadEvent event) {
        try {
            final String message = client.receive();
            System.out.println("MESSAGE AS PLAIN TEXT (REMOVE AFTER TESTING): " + message );
            @NotNull ObjectMapper objectMapper = new ObjectMapper();
            @NotNull Packet packet = objectMapper.readValue( message, Packet.class );

            switch( packet.getType() ) {
                case PING_RESPONSE:
                    System.out.println("Ping response received.");
                    break;

                case REGISTRY_RESPONSE:
                    System.out.println("Registry response received.");
                    break;

                case LOGIN_RESPONSE:
                    //serverLogoutEvent.fireAsync( new ServerLogoutEvent( socket, message ) );
                    System.out.println("Login response received.");
                    break;

                case LOGOUT_RESPONSE:
                    //serverLogoutEvent.fireAsync( new ServerLogoutEvent( socket, message ) );
                    System.out.println("Logout response received.");
                    break;

                case BROADCAST_RESPONSE:
                    //serverBroadcastEvent.fireAsync( new ServerBroadcastEvent( socket, message ) );
                    System.out.println("Broadcast response received.");
                    break;

                case UNICAST_RESPONSE:
                    //serverUnicastEvent.fireAsync( new ServerUnicastEvent( socket, message ) );
                    System.out.println("Unicast response received.");
                    break;

                case SETNICKNAME_RESPONSE:
                    //serverSetNicknameEvent.fireAsync( new ServerSetNicknameEvent( socket, message) );
                    break;

                case UNICAST_MESSAGE:
                    //serverUnicastEvent.fireAsync( new ServerUnicastEvent( socket, message ) );
                    System.out.println("New incoming message received.");
                    break;

                default:
                    System.out.println("Undefined message type.");
                    break;
            }

            clientMessageReadEvent.fireAsync( new ClientMessageReadEvent() );
        }
        catch (final IOException exception) {
            System.out.println("Connection stopped by server.");
            client.exit();
        }
    }
}
