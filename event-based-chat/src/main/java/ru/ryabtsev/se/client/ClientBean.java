package ru.ryabtsev.se.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.ryabtsev.se.User;
import ru.ryabtsev.se.configuration.NetworkConfiguration;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.client.event.ClientMessageReadEvent;
import ru.ryabtsev.se.logging.LogFile;
import ru.ryabtsev.se.logging.Logable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Console client implementation.
 */
@ApplicationScoped
@NoArgsConstructor
public class ClientBean implements Client {

    /**
     * Network configuration.
     */
    @Inject
    private NetworkConfiguration networkConfiguration;

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;


    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    /**
     * User data like login, password, e.t.c.
     */
    private User userData;

    private Logable log;

    private boolean isAuthorized = false;

    /**
     * @InheritDoc
     */
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

    /**
     * @InheritDoc
     */
    @Override
    @SneakyThrows
    public void send( final String message ) {
        out.writeUTF( message );
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isAuthorized() {
        return isAuthorized;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setAuthorized(boolean isAuthorized) {
        final String logFileSuffix = "-messages.txt";
        this.isAuthorized = isAuthorized;
        log = new LogFile(userData.getLogin() + logFileSuffix);
    }

    /**
     * @inheritDoc
     */
    @Override
    public User getUser() {
        return userData;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setUser(User user) {
        userData = user;
    }

    /**
     * @InheritDoc
     */
    @Override
    public String receive() throws IOException {
        try {
            return in.readUTF();
        }
        catch (IOException exception) {
            System.out.println("Client receive() exception");
            throw exception;
        }
    }

    /**
     * @InheritDoc
     */
    @Override
    @SneakyThrows
    public void exit( ) {
        socket.close();
        System.out.println( "Client disconnected." );
        System.exit( 0 );
    }

    @Override
    public void clear() {
        log.clear();
    }

    @Override
    public void write(String string) {
        log.write( string );
    }

    @Override
    public List<String> readAll() {
        return log.readAll();
    }

    @Override
    public List<String> readLast(int stringsNumber) {
        return log.readLast( stringsNumber );
    }
}
