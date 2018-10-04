package ru.ryabtsev.se.server;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerTaskMessageReceive extends ServerTask {

    private final Socket socket;

    public ServerTaskMessageReceive(final Server server, final Socket socket ) {
        super( server );
        this.socket = socket;
    }

    @Override
    public void run() {
        final Server server = getServer();
        try {
            System.out.println("in.readUTF()");
            final DataInputStream in = new DataInputStream( socket.getInputStream() );
            final String message = in.readUTF();
            System.out.println("Message received: " + message );
            server.run( new ServerTaskMessageReceive( server, socket ) );
            server.run( new ServerTaskMessageBroadcast( server, message ) );
        }
        catch ( Exception exception ) {
            server.remove( socket );
            exception.printStackTrace();
        }
    }
}
