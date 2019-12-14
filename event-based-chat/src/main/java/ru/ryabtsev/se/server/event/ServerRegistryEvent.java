package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerRegistryEvent extends ServerMessageEvent {
    public ServerRegistryEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
