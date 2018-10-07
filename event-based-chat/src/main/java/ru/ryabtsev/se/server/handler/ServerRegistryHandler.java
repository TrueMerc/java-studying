package ru.ryabtsev.se.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.packets.PacketRegistry;
import ru.ryabtsev.se.server.ConnectionService;
import ru.ryabtsev.se.server.UserService;
import ru.ryabtsev.se.server.event.ServerRegistryEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerRegistryHandler {

    @Inject
    UserService userService;

    @Inject
    ConnectionService connectionService;

    @SneakyThrows
    public void handle(@ObservesAsync final ServerRegistryEvent event) {
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessage();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketRegistry packet = objectMapper.readValue( message, PacketRegistry.class );
        final boolean result = userService.registry( packet.getLogin(), packet.getPassword() );
        connectionService.sendResult( socket, result );
    }
}
