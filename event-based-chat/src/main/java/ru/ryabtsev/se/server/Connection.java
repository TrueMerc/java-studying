package ru.ryabtsev.se.server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class Connection {
    private final String id = UUID.randomUUID().toString();

    private final Socket socket;
    
    private String login;

    public Connection( Socket socket ) {
        this.socket = socket;
        login = "";
    }

    /**
     * Sends message.
     * @param message - message text.
     */
    @SneakyThrows
    public void send( final String message ) {
        final DataOutputStream outputStream = new DataOutputStream( socket.getOutputStream() );
        outputStream.writeUTF( message );
    }
}
