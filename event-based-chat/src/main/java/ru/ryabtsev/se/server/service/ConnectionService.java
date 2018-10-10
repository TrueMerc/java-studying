package ru.ryabtsev.se.server.service;

import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.PacketType;
import ru.ryabtsev.se.server.Connection;

import java.net.Socket;

/** Server connection service interface */
public interface ConnectionService {

    @Nullable Connection get(Socket socket);

    @Nullable Connection getByLogin( String login );

    void add(@Nullable Socket socket);

    void remove(@Nullable Socket socket);

    void setLogin(@Nullable Socket socket, @Nullable String login);

    void setResult(@Nullable Socket socket, @Nullable Boolean success);

    //void sendResult(@Nullable Socket socket, @Nullable PacketType packetType);

    void sendResult(@Nullable Socket socket, @Nullable PacketType packetType, @Nullable Boolean success);

    void sendMessage(@Nullable Connection connection, @Nullable String login, @Nullable String message);

    void sendUnicast(@Nullable Connection connection, @Nullable String login, @Nullable String message);

    void sendBroadcast(@Nullable String login, @Nullable String message);

    void disconnect(@Nullable Socket socket);
}
