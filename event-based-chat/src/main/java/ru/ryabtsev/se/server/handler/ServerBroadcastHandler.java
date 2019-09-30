package ru.ryabtsev.se.server.handler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.broadcast.PacketBroadcastRequest;
import ru.ryabtsev.se.server.Connection;
import ru.ryabtsev.se.server.service.ConnectionServiceBean;
import ru.ryabtsev.se.server.event.ServerBroadcastEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerBroadcastHandler {

    @Inject
    ConnectionServiceBean connectionService;

    @SneakyThrows
    public void handle(@ObservesAsync final ServerBroadcastEvent event) {
        System.out.println("Server broadcast handler");
        @NotNull final Socket socket = event.getSocket();
        @Nullable final Connection connection = connectionService.get( socket );
        if( connection == null ) {
            System.out.println( "Can't find connection.");
            return;
        }
        @Nullable final String login = connection.getLogin();
        if( login == null || login.isEmpty() ) {
            System.out.println( "Can't find connection login.");
            return;
        }
        @NotNull final String message = event.getMessage();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketBroadcastRequest packetBroadcastRequest = objectMapper.readValue( message, PacketBroadcastRequest.class );
        @NotNull final String broadcastMessage =  packetBroadcastRequest.getMessage();

        System.out.println("Sending broadcast message.");
//        for( final Connection receiverConnection : connectionService.getConnections() ) {
//
//            connectionService.sendBroadcastToConnection( receiverConnection, login,  broadcastMessage );
//        }
        connectionService.sendBroadcast( login, broadcastMessage );
    }
}
