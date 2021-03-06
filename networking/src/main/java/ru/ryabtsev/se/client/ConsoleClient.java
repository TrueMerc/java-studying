package ru.ryabtsev.se.client;

import lombok.SneakyThrows;
import ru.ryabtsev.se.NetworkConfiguration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;

/**
 * Console client class.
 */
public class ConsoleClient implements Client {

    private Socket socket;

    private final NetworkConfiguration networkConfiguration;

    private final ExecutorService executorService;

    private DataInputStream inputStream;

    private DataOutputStream outputStream;


    /**
     * Constructor.
     * @param networkConfiguration - network configuration parameters.
     * @param executorService - executor service instance.
     */
    public ConsoleClient(final NetworkConfiguration networkConfiguration, final ExecutorService executorService ) {
        this.networkConfiguration = networkConfiguration;
        this.executorService = executorService;
    }

    /**
     * Returns input data stream.
     * @return Input data stream associated with current runner.
     */
    public DataInputStream getInputStream() {
        return inputStream;
    }

    /**
     * Returns output data stream.
     * @return Output data stream associated with current runner.
     */
    public DataOutputStream getOutputStream() { return outputStream; }

    @Override
    @SneakyThrows( IOException.class )
    public void run() {
        final String host = networkConfiguration.getHost();
        final Integer port = networkConfiguration.getPort();
        try {
            socket = new Socket(host, port);
        }
        catch( IOException exception ) { // Process case when server isn't started.
            System.out.println( "Can't connect to chat server.");
            System.out.println( "Try to restart chat." );
            this.exit();
        }
        inputStream =  new DataInputStream(  socket.getInputStream() );
        outputStream = new DataOutputStream( socket.getOutputStream() );

        run( new ClientTaskMessageInput( this ) );
        run( new ClientTaskMessageReceive( this ) );
    }

    @Override
    public void run( ClientTask task ) {
        if( task == null ) {
            return;
        }
        executorService.submit( task );
    }

    @Override
    public void send( final String message ) {
        if( message == null || message.isEmpty() ) {
            return;
        }
        run( new ClientTaskMessageSend( this, message ) );
    }

    @Override
    @SneakyThrows
    public void exit() {
        if( socket != null && socket.isConnected() ) {
            socket.close();
        }
        System.exit( 0);
    }
}
