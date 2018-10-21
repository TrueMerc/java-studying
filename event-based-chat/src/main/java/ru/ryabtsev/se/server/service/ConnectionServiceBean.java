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
import ru.ryabtsev.se.packets.unicast.PacketUnicastMessage;
import ru.ryabtsev.se.server.Connection;

import javax.enterprise.context.ApplicationScoped;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages chat connections.
 */
@NoArgsConstructor
@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionServiceBean implements ConnectionService {

    private final static long AUTHORIZATION_TIMEOUT = 15000;

    private final Map<Socket, Connection> connections = new LinkedHashMap<>();

    /**
     * Returns connection list.
     * @return Connection list.
     */
    @NotNull
    public Map<Socket, Connection> getConnections() {
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

        return connections.get( socket );
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
        for(final Map.Entry<Socket, Connection> connection : connections.entrySet() ) {
            if( connection.getValue().getLogin().equals( login ) ) {
                return connection.getValue();
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
        connections.put( socket, connection );
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
        connections.remove( socket );
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
            connection.setAuthorized( true );
        }
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
    public void sendUnicast(@Nullable Connection connection, @Nullable String login, @Nullable String message) {
        if( connection == null || connection.getLogin() == null ) {
            return;
        }

        @NotNull final PacketUnicastMessage packet = new PacketUnicastMessage( login, message );
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        connection.send( objectMapper.writeValueAsString( packet ) );
    }

    @Override
    public void sendBroadcast(@Nullable String login, @Nullable String message) {
        for( final Map.Entry<Socket, Connection> receiverConnection : connections.entrySet() ) {
            sendMessage( receiverConnection.getValue(), login,  message );
        }
    }

    @Override
    @SneakyThrows
    public void sendMessage(@Nullable Connection connection, @Nullable String login, @Nullable String message) {
        if( connection == null || connection.getLogin() == null ) {
            return;
        }

        @NotNull final PacketBroadcastResponse packet = new PacketBroadcastResponse();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
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

    /**
     * Kick unauthorized users by timeout.
     */
    @Override
    public void kickByTimeout() {
        final long currentTime = System.currentTimeMillis();
        final List<Socket> removedSockets = new ArrayList<>();
        for(final Map.Entry<Socket, Connection> connectionEntry : connections.entrySet() ) {
            final Connection connection = connectionEntry.getValue();
            if( ( connection.isAuthorized()  == false) && ((currentTime - connection.getStartTime()) > AUTHORIZATION_TIMEOUT) ) {
                removedSockets.add( connectionEntry.getKey() );
            }
        }
        System.out.println(removedSockets.size() + " connection(s) will be closed.");
        for(final Socket socket : removedSockets ) {
            disconnect( socket );
        }
    }
}
