package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.client.ClientBean;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageLoginEvent;
import ru.ryabtsev.se.client.event.ClientMessageSetNicknameEvent;
import ru.ryabtsev.se.packets.nickname.PacketSetNicknameRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@NoArgsConstructor
@ApplicationScoped
public class ClientMessageSetNicknameHandler {

    @Inject
    private ClientBean client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientMessageSetNicknameEvent> clientMessageSetNicknameEvent;

    @SneakyThrows
    public void handler(@Observes final ClientMessageSetNicknameEvent event) {
        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Login: ");
        @NotNull final String login = in.nextLine();

        System.out.println("Set new nickname: ");
        @NotNull final String nickname = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketSetNicknameRequest packet = new PacketSetNicknameRequest();
        packet.setLogin( login );
        packet.setNickname( nickname );

        client.send( objectMapper.writeValueAsString(packet) );
        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }
}