package ru.ryabtsev.se.server.handler;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.PacketType;
import ru.ryabtsev.se.packets.unicast.PacketUnicastRequest;
import ru.ryabtsev.se.server.Connection;
import ru.ryabtsev.se.server.event.ServerUnicastEvent;
import ru.ryabtsev.se.server.service.ConnectionServiceBean;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

/**
 * Handles unicast message request.
 */
@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerUnicastHandler {

    @Inject
    ConnectionServiceBean connectionService;

    @SneakyThrows
    public void handle(@ObservesAsync final ServerUnicastEvent event) {
        System.out.println("Server unicast handler");
        @NotNull final Socket socket = event.getSocket();
        @Nullable final Connection connection = connectionService.get( socket );
        if( connection == null ) {
            System.out.println( "Can't find connection.");
            return;
        }
        @Nullable final String sender = connection.getLogin();
        if( sender == null || sender.isEmpty() ) {
            System.out.println( "Can't find connection login.");
            return;
        }
        @NotNull final String message = event.getMessage();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketUnicastRequest packetUnicastRequest = objectMapper.readValue( message, PacketUnicastRequest.class );
        @NotNull final String receiver = packetUnicastRequest.getReceiverLogin();
        @NotNull final String unicast =  packetUnicastRequest.getMessage();

        System.out.println("Sending unicast message.");
        boolean result = connectionService.sendUnicast( sender, receiver, unicast );

        connectionService.sendResult( socket, PacketType.UNICAST_RESPONSE, result );
    }
}
