package ru.ryabtsev.se.server.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;
import ru.ryabtsev.se.packets.broadcast.PacketBroadcastResponse;
import ru.ryabtsev.se.server.Connection;

import javax.enterprise.context.ApplicationScoped;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages chat connections.
 */
@NoArgsConstructor
@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionServiceBean implements ConnectionService {

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
     * Returns connection with specified client login.
     * @param login - client login.
     * @return Connection matches specified client socket.
     */
    @Nullable
    public Connection getByLogin( String login ) {
        if( login == null ) {
            return null;
        }
        for(final Connection connection : connections) {
            if( connection.getLogin().equals( login ) ) {
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
        Connection connection = get(socket);
        if( connection != null ) {
            connection.setLogin( login );
        }
    }

    /**
     * Sends operation result to client which specified by its socket.
     * @param socket - client socket.
     * @param success - operation result login.
     */
    @Override
    public void setResult(@Nullable Socket socket, @Nullable Boolean success) {

    }

    @Override
    public void sendResult(@Nullable Socket socket, @Nullable PacketType packetType, @Nullable Boolean success) {
        try {
            @NotNull final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            @NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull final PacketResult packet = new PacketResult(success);
            packet.setType( packetType );
            out.writeUTF( objectMapper.writeValueAsString(packet) );
        }
        catch (IOException exception) {
            System.out.println( "Socket exception.");
        }

        System.out.println( "Result was sent." );
    }

    @Override
    @SneakyThrows
    public void sendMessage(@Nullable Connection connection, @Nullable String login, @Nullable String message) {
        if( connection == null || connection.getLogin() == null ) {
            return;
        }
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketBroadcastResponse packet = new PacketBroadcastResponse();
        packet.setLogin( login );
        packet.setMessage( message );
        connection.send( objectMapper.writeValueAsString( packet ) );
    }


    /**
     * Close connection to specified client socket.
     */
    @SneakyThrows
    public void disconnect(@Nullable final Socket socket ) {
        socket.close();
        remove( socket );
    }
}
