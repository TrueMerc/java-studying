package ru.ryabtsev.se.server;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages chat connections.
 */
public class ConnectionService {

    private final List<Connection> connections = new ArrayList<>();

    /**
     * Returns connection list.
     * @return Connection list.
     */
    @NotNull
    public List<Connection> getConnections() {
        return connections;
    }

    /**
     * Returns connection with specified socket.
     * @param socket - client socket.
     * @return Connection matches specified client socket.
     */
    @Nullable
    public Connection get(@Nullable final Socket socket ) {
        if( socket == null ) {
            return null;
        }
        for(final Connection connection : connections) {
            if( connection.getSocket().equals( socket ) ) {
                return connection;
            }
        }
        return null;
    }

    /**
     * Adds connection with specified socket.
     * @param socket - client socket.
     */
    public void add(@Nullable final Socket socket ) {
        if( socket == null ) {
            return;
        }
        final Connection connection = new Connection( socket );
        connections.add( connection );
        System.out.println("Added connection with id = " + connection.getId() + "...");
    }


    /**
     * Removes connection with specified socket.
     * @param socket - client socket.
     */
    public void remove(@Nullable final Socket socket ) {
        if( socket == null ) {
            return;
        }
        for(final Connection connection : connections) {
            if( connection.getSocket().equals( socket ) ) {
                connections.remove( socket );
                System.out.println("Removed connection with id = " + connection.getId() + "...");
            }
        }
    }

    /**
     * Sets login for specified socket.
     * @param socket - client socket.
     * @param login - client login.
     */
    public void setLogin(@Nullable final Socket socket, @Nullable final String login) {

    }

    /**
     * Sends operation result to client which specified by its socket.
     * @param socket - client socket.
     * @param success - operation result login.
     */
    public void sendResult(@Nullable final Socket socket, @Nullable Boolean success) {

    }

    /**
     * Close connection to specified client socket.
     */
    @SneakyThrows
    public void disconnect(@Nullable final Socket socket ) {
        socket.close();
        remove( socket );
    }


    public void sendMessage(Connection receiverConnection, String login, String message) {
    }
}
