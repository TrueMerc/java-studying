package ru.ryabtsev.se.client;

import lombok.SneakyThrows;
import ru.ryabtsev.se.Application;
import ru.ryabtsev.se.NetworkConfiguration;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageReadEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientApplication implements Application {


    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    NetworkConfiguration networkConfiguration;

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

    @SneakyThrows
    public void send( final String message ) {
        out.writeUTF( message );
    }

    @SneakyThrows
    public void exit( ) {
        socket.close();
        System.out.println( "Client disconnected." );
        System.exit( 0 );
    }

}
