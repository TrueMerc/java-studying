package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerLogoutEvent extends ServerMessageEvent {
    public ServerLogoutEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
