package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerBroadcastEvent extends ServerMessageEvent {
    public ServerBroadcastEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
