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
import ru.ryabtsev.se.client.event.ClientMessageBroadcastEvent;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.packets.broadcast.PacketBroadcastRequest;

@NoArgsConstructor
@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientMessageBroadcastHandler {
    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageBroadcastEvent> clientMessageBroadcastEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handle(@Observes ClientMessageBroadcastEvent event) {
        System.out.println("Enter your message");
        @NotNull final Scanner in = new Scanner(System.in);
        @NotNull final String message = in.nextLine();
        @NotNull final PacketBroadcastRequest packet = new PacketBroadcastRequest();
        packet.setMessage( message );
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        client.send( objectMapper.writeValueAsString( packet ) );
        client.write( "Me to all: " + message );
        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }
}
