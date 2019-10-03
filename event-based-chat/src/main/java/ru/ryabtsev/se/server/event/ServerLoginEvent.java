package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerLoginEvent extends ServerMessageEvent {
    public ServerLoginEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
