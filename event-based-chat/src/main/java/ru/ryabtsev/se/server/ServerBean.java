package ru.ryabtsev.se.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Logger;
import ru.ryabtsev.se.configuration.NetworkConfiguration;
import ru.ryabtsev.se.server.event.ServerCheckAuthorizationEvent;
import ru.ryabtsev.se.server.event.ServerConnectionEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.net.ServerSocket;

/**
 * Chat console server class.
 */
@Getter
@NoArgsConstructor
@ApplicationScoped
public class ServerBean implements Server {

    @Inject
    private NetworkConfiguration networkConfiguration;

    @Inject
    private Event<ServerConnectionEvent> serverConnectionEvent;

    @Inject
    private Event<ServerCheckAuthorizationEvent> serverCheckAuthorizationEvent;

    @Inject
    private transient Logger logger;

    private ServerSocket serverSocket;

    @Override
    @SneakyThrows
    public void run() {
        logger.warn( "Server started.");
        serverCheckAuthorizationEvent.fireAsync( new ServerCheckAuthorizationEvent() );
        serverSocket = new ServerSocket( networkConfiguration.getPort() );
        serverConnectionEvent.fire( new ServerConnectionEvent() );

    }
}
