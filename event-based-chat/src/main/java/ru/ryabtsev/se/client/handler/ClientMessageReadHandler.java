package ru.ryabtsev.se.client.handler;

import lombok.SneakyThrows;
import ru.ryabtsev.se.client.Client;
import ru.ryabtsev.se.client.event.ClientMessageReadEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class ClientMessageReadHandler {

    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    public void handle(@ObservesAsync ClientMessageReadEvent event) {
        try {
            final String message = client.getIn().readUTF();
            System.out.println("New message: " + message );
            clientMessageReadEvent.fireAsync( new ClientMessageReadEvent() );
        }
        catch (final IOException exception) {
            //exception.printStackTrace();
            System.out.println("Connection stopped by server.");
            client.exit();
        }
    }
}
