package ru.ryabtsev.se.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.ryabtsev.se.configuration.NetworkConfiguration;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageReadEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Console client implementation
 */
@Getter
@ApplicationScoped
@NoArgsConstructor
public class ClientBean implements Client {

    @Inject
    NetworkConfiguration networkConfiguration;

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    @Override
    @SneakyThrows
    public void run() {
        final String host = networkConfiguration.getHost();
        final Integer port = networkConfiguration.getPort();
        socket = new Socket( host, port );
        in = new DataInputStream( socket.getInputStream() );
        out = new DataOutputStream( socket.getOutputStream() );
        clientMessageReadEvent.fireAsync( new ClientMessageReadEvent() );
        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }

    @Override
    @SneakyThrows
    public void send( final String message ) {
        out.writeUTF( message );
    }

    @Override
    public String receive() {
        String result = null;
        try {
            result = in.readUTF();
        }
        catch (IOException e) {
            System.out.println("Client receive() exception");
        }

        return result;
    }

    @SneakyThrows
    public void exit( ) {
        socket.close();
        System.out.println( "Client disconnected." );
        System.exit( 0 );
    }

}
