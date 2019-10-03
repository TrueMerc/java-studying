package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.client.Client;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessagePingEvent;
import ru.ryabtsev.se.packets.ping.PacketPingRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;

@ApplicationScoped
public class ClientMessagePingHandler {

    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientMessagePingEvent> clientMessagePingEvent;

    @SneakyThrows
    public void handle(@ObservesAsync final ClientMessagePingEvent event) {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketPingRequest packet = new PacketPingRequest();
        client.send( objectMapper.writeValueAsString(packet) );
        System.out.println("Ping message was sent.");
    }
}
