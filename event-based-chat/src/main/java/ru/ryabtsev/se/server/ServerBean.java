package ru.ryabtsev.se.server;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.ryabtsev.se.application.NetworkConfiguration;
import ru.ryabtsev.se.server.event.ServerConnectionEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.net.ServerSocket;

/**
 * Chat console server class.
 */
@Getter
@ApplicationScoped
public class ConsoleServer implements ServerApplication {

    @Inject
    private NetworkConfiguration networkConfiguration;

    @Inject
    private Event<ServerConnectionEvent> serverConnectionEvent;

    private ServerSocket socket;


    @Override
    @SneakyThrows
    public void run() {
        socket = new ServerSocket( networkConfiguration.getPort() );
        serverConnectionEvent.fire( new ServerConnectionEvent() );
    }
}
