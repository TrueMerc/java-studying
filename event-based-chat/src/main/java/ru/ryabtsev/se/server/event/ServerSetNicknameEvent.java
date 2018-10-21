package ru.ryabtsev.se.server.event;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;

public class ServerSetNicknameEvent extends ServerMessageEvent {
    public ServerSetNicknameEvent(@NotNull Socket socket, @NotNull String message) {
        super( socket, message );
    }
}
