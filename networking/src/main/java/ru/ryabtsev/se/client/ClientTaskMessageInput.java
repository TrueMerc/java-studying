package ru.ryabtsev.se.client;

import java.util.Scanner;

public class ClientTaskMessageInput extends ClientTask {
    private final static String CMD_EXIT = "-exit";

    public ClientTaskMessageInput( Client client ) {
        super( client );
    }

    @Override
    public void run() {
        System.out.println( "Enter message or exit command (-exit)");
        final Scanner input = new Scanner( System.in );
        final String message = input.nextLine();

        if( CMD_EXIT.equals( message ) ) {
            client.exit();
        }

        client.send( message );
        System.out.println("my >: " + message );
        client.run( new ClientTaskMessageInput( client ) );
    }
}
