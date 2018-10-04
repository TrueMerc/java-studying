package ru.ryabtsev.se.server;

import lombok.SneakyThrows;

import java.net.Socket;

public class ServerTaskConnection extends ServerTask {
    public ServerTaskConnection( final Server server ) {
        super( server );
    }

    @Override
    @SneakyThrows
    public void run() {
        System.out.println("getServerSocket().accept()");
        final Server server = getServer();
        final Socket socket = server.getServerSocket().accept(); // Starts infinite loop while waiting new client.
        server.run( new ServerTaskConnection( server ) );
        server.run( new ServerTaskMessageReceive( server, socket ) );
        server.add( socket ); // FIXME Try to move this string for two strings upper.
    }
}
