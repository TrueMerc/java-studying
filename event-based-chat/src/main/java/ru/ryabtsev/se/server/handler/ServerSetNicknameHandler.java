package ru.ryabtsev.se.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.packets.PacketType;

import ru.ryabtsev.se.packets.nickname.PacketSetNicknameRequest;
import ru.ryabtsev.se.server.event.ServerSetNicknameEvent;
import ru.ryabtsev.se.server.service.ConnectionServiceBean;
import ru.ryabtsev.se.server.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;


@ApplicationScoped
public class ServerSetNicknameHandler {

    @Inject
    private UserService userService;

    @Inject
    private ConnectionServiceBean connectionService;

    @SneakyThrows
    public void handle(@ObservesAsync final ServerSetNicknameEvent event) {
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessage();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketSetNicknameRequest packet = objectMapper.readValue(message, PacketSetNicknameRequest.class);
        @NotNull final String login = packet.getLogin();
        @NotNull final String nickname = packet.getNickname();
        boolean check = userService.setNickname( login, nickname );
        if( check ) {
            System.out.println("User " + login + " successfully changed nickname.");
            connectionService.authorize( socket, login );
        }
        else {
            System.out.println( "Nickname change failed." );
        }
        connectionService.sendResult( socket, PacketType.SETNICKNAME_RESPONSE, check );
    }
}
