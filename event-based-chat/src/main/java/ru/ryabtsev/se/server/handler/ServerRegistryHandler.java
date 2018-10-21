package ru.ryabtsev.se.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.packets.PacketType;
import ru.ryabtsev.se.packets.registry.PacketRegistryRequest;
import ru.ryabtsev.se.server.service.ConnectionService;
import ru.ryabtsev.se.server.service.UserService;
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
        @NotNull final PacketRegistryRequest packet = objectMapper.readValue( message, PacketRegistryRequest.class );
        boolean result = userService.registry( packet.getLogin(), packet.getPassword() );
        if( result ) {
            System.out.println("User " + packet.getLogin() + " registered successfully.");
        }
        connectionService.sendResult( socket, PacketType.REGISTRY_RESPONSE, result );
    }
}
