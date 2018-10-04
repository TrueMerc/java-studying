package ru.ryabtsev.se.client;

import java.io.IOException;

public class ClientTaskMessageReceive extends ClientTask {
    public ClientTaskMessageReceive( final Client client ) {
        super( client );
    }

    @Override
    public void run() {
        try {
            final String message = client.getInputStream().readUTF();
            System.out.println("***" + message + "***");
            client.run( new ClientTaskMessageReceive( client ) );
        }
        catch (IOException exception) {
            client.exit();
        }
    }
}
