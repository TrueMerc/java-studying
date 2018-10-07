package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.client.ClientApplication;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageLoginEvent;
import ru.ryabtsev.se.packets.PacketLogin;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientMessageLoginHandler {

    @Inject
    private ClientApplication client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientMessageLoginEvent> clientMessageLoginEvent;

    @SneakyThrows
    public void handler(@Observes final ClientMessageLoginEvent event) {
        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Login: ");
        @NotNull final String login = in.nextLine();

        System.out.println("Password: ");
        @NotNull final String password = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packet = new PacketLogin();
        packet.setLogin( login );
        packet.setPassword( password );

        client.send( objectMapper.writeValueAsString(packet) );
        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }
}
