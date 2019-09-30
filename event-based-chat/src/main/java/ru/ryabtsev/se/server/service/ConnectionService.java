package ru.ryabtsev.se.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.PacketType;
import ru.ryabtsev.se.server.Connection;

import java.net.Socket;

/** Server connection service interface */
public interface ConnectionService {

    /**
     * Returns connection by given socket.
     * @param socket - socket.
     * @return connection object.
     */
    @Nullable Connection get( Socket socket );

    /**
     * Returns connection by given login.
     * @param login - login.
     * @return connection.
     */
    @Nullable Connection get(String login );

    /**
     * Adds new connection with specified socket.
     * @param socket - client socket.
     */
    void add(@Nullable Socket socket);

    /**
     * Removes existing connection with specified socket.
     * @param socket - client socket.
     */
    void remove(@Nullable Socket socket);

    /**
     * Authorizes client with given login for specified connection socket.
     * @param socket - client socket.
     * @param login - client login.
     */
    void authorize(@Nullable Socket socket, @Nullable String login);

    /**
     * Sends server response packet to client specified by socket.
     * @param socket - socket.
     * @param packetType - packet type.
     * @param success - result of requested operation.
     */
    void sendResult(@Nullable Socket socket, @Nullable PacketType packetType, @Nullable Boolean success);

    /**
     * Sends unicast message.
     * @param sender - message sender login.
     * @param receiver - message receiver login.
     * @param message - message text.
     */
    boolean sendUnicast(@NotNull final String sender, @NotNull final String receiver, @NotNull final String message);

    /**
     * Sends broadcast message.
     * @param sender - message sender login.
     * @param message - message text.
     */
    void sendBroadcast(@NotNull final String sender, @NotNull final String message);

    /**
     * Close connection with specified socket.
     * @param socket - socket.
     */
    void disconnect(@Nullable Socket socket);

    /**
     * Kick all unauthorized users by timeout.
     */
    void kickByTimeout();
}
