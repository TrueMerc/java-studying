package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerMessageInputEvent extends ServerMessageEvent {
    public ServerMessageInputEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
