package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.enterprise.event.Event;
import java.util.Scanner;

import ru.ryabtsev.se.client.Client;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageUnicastEvent;
import ru.ryabtsev.se.packets.unicast.PacketUnicastRequest;

@NoArgsConstructor
@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientMessageUnicastHandler {
    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handle(@Observes ClientMessageUnicastEvent event) {
        System.out.println("Enter recipient (by login):");
        @NotNull final Scanner in = new Scanner(System.in);
        @NotNull final String recipient = in.nextLine();
        System.out.println("Enter message text");
        @NotNull final String message = in.nextLine();
        @NotNull final PacketUnicastRequest packet = new PacketUnicastRequest( recipient, message);

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        client.send( objectMapper.writeValueAsString( packet ) );
        client.write( "Me to " + recipient + ": " + message);
        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }
}
