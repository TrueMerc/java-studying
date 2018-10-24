package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.client.ClientBean;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageLoginEvent;
import ru.ryabtsev.se.client.event.ClientMessageRegistryEvent;
import ru.ryabtsev.se.packets.login.PacketLoginRequest;
import ru.ryabtsev.se.packets.registry.PacketRegistryRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientMessageRegistryHandler {

    @Inject
    private ClientBean client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handler(@Observes final ClientMessageRegistryEvent event) {
        System.out.println( "Client registry handler." );

        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Login: ");
        @NotNull final String login = in.nextLine();

        System.out.println("Password: ");
        @NotNull final String password = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketRegistryRequest packet = new PacketRegistryRequest( login, password );

        client.send( objectMapper.writeValueAsString(packet) );
        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }

}
