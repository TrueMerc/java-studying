package ru.ryabtsev.se.server;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerTaskMessageRead extends ServerTask {

    //private final Socket socket;
    private Socket socket;

    public ServerTaskMessageRead( final Server server, final Socket socket ) {
        super( server );
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("in.readUTF()");
            final DataInputStream in = new DataInputStream( socket.getInputStream() );
            final String message = in.readUTF();
            System.out.println("Message received: " + message );
            server.run( new ServerTaskMessageRead( server, socket ) );
            server.run( new ServerTaskMessageBroadcast( server, message ) );
        }
        catch ( Exception exception ) {
            server.remove( socket );
            exception.printStackTrace();
        }
    }
}
