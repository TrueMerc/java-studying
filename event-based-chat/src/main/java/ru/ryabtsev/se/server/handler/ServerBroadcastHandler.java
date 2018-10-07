package ru.ryabtsev.se.server.handler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.server.Connection;
import ru.ryabtsev.se.server.ConnectionService;
import ru.ryabtsev.se.server.event.ServerBroadcastEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerBroadcastHandler {

    @Inject
    ConnectionService connectionService;

    public void handle(@ObservesAsync final ServerBroadcastEvent event) {
        @NotNull final Socket socket = event.getSocket();
        @Nullable final Connection connection = connectionService.get( socket );
        if( connection == null ) {
            return;
        }
        @Nullable final String login = connection.getLogin();
        if( login == null || login.isEmpty() ) {
            return;
        }
        @NotNull final String message = event.getMessage();
        for( final Connection receiverConnection : connectionService.getConnections() ) {
            connectionService.sendMessage( receiverConnection, login, message );
        }
    }
}
