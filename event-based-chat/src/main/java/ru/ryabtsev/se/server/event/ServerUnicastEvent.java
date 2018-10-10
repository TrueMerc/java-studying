package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerUnicastEvent extends ServerMessageEvent {
    public ServerUnicastEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
