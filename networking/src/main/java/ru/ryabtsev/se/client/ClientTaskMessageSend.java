package ru.ryabtsev.se.client;

import java.io.IOException;

public class ClientTaskMessageSend extends ClientTask {

    private final String message;

    public ClientTaskMessageSend( Client client, String message ) {
        super( client );
        this.message = message;
    }

    @Override
    public void run() {
        try {
            System.out.println( "Message sending: " + message );
            client.getOutputStream().writeUTF(message);
        }
        catch (IOException exception) {
            exception.printStackTrace();
            client.exit();
        }
    }
}
