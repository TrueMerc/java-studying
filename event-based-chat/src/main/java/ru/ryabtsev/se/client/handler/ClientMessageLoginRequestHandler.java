package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.User;
import ru.ryabtsev.se.client.Client;
import ru.ryabtsev.se.client.ClientBean;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageLoginRequestEvent;
import ru.ryabtsev.se.packets.login.PacketLoginRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientMessageLoginRequestHandler {

    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientMessageLoginRequestEvent> clientMessageLoginEvent;

    @SneakyThrows
    public void handler(@Observes final ClientMessageLoginRequestEvent event) {
        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Login: ");
        @NotNull final String login = in.nextLine();

        System.out.println("Password: ");
        @NotNull final String password = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLoginRequest packet = new PacketLoginRequest();
        packet.setLogin( login );
        packet.setPassword( password );

        client.send( objectMapper.writeValueAsString(packet) );
        client.setUser( new User( login, password, login ) );
        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }
}
