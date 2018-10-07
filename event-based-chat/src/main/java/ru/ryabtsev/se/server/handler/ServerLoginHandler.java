package ru.ryabtsev.se.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.packets.PacketLogin;
import ru.ryabtsev.se.server.ConnectionService;
import ru.ryabtsev.se.server.UserService;
import ru.ryabtsev.se.server.event.ServerLoginEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerLoginHandler {

    @Inject
    private UserService userService;

    @Inject
    ConnectionService connectionService;

    @SneakyThrows
    public void handle(@ObservesAsync final ServerLoginEvent event) {
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessage();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packet = objectMapper.readValue(message, PacketLogin.class);
        @NotNull final String login = packet.getLogin();
        boolean check = userService.check( login, packet.getPassword() );
        if( check ) {
            connectionService.setLogin( socket, login );
        }
        else {
            System.out.println( "Incorrect password for login " + login);
        }
        connectionService.sendResult( socket, check );
    }
}
