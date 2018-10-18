package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerPingEvent extends ServerMessageEvent {
    public ServerPingEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
