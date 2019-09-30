package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.ryabtsev.se.client.Client;
import ru.ryabtsev.se.client.event.ClientMessageLoginResponseEvent;
import ru.ryabtsev.se.packets.login.PacketLoginResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.util.List;

/**
 * Handles login response events on client side.
 */
@ApplicationScoped
public class ClientMessageLoginResponseHandler {

    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageLoginResponseEvent> clientMessageLoginResponseEvent;

    @SneakyThrows
    public void handle(@ObservesAsync ClientMessageLoginResponseEvent event) {
        final String message = event.getMessage();
        final ObjectMapper objectMapper = new ObjectMapper();
        final PacketLoginResponse packet = objectMapper.readValue( message, PacketLoginResponse.class );
        final boolean loginResult = packet.isSuccess();

        client.setAuthorized( loginResult );

        final String messageToUser = loginResult ? "Successfully logged in." : "Log in failed.";
        System.out.println( messageToUser );

        if( loginResult ) {
            final int PRINT_LAST_MESSAGES_NUMBER = 100;
            List<String> lastMessages = client.readLast( PRINT_LAST_MESSAGES_NUMBER );
            if(lastMessages != null) {
                System.out.println( "Last messages:" );
                for( String logMessage : lastMessages ) {
                    System.out.println( logMessage );
                }
            }
        }
    }
}
