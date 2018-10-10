package ru.ryabtsev.se.server.handler;

import ru.ryabtsev.se.packets.PacketType;
import ru.ryabtsev.se.server.Connection;
import ru.ryabtsev.se.server.service.ConnectionService;
import ru.ryabtsev.se.server.service.ConnectionServiceBean;
import ru.ryabtsev.se.server.event.ServerPingEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;

@ApplicationScoped
public class ServerPingHandler {

    @Inject
    ConnectionService connectionService;

    public void handle(@ObservesAsync final ServerPingEvent event) {
        System.out.println("Server ping handler");
        final Connection connection = connectionService.get( event.getSocket() );
        if( connection == null ) {
            return;
        }
        System.out.println("Ping from connection " + connection.getId() );
        connectionService.sendResult( event.getSocket(), PacketType.PING_RESPONSE, true );
    }
}
