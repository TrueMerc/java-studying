package ru.ryabtsev.se.server;

public class ServerTaskMessageBroadcast extends ServerTask {

    private String message;

    public ServerTaskMessageBroadcast( Server server, String message ) {
        super( server );
        this.message = message;
    }

    @Override
    public void run( ) {
        System.out.println( "Broadcasting: " + message );
        for(final Connection connection : getServer().connections() ) {
            connection.send( message );
        }
    }
}
