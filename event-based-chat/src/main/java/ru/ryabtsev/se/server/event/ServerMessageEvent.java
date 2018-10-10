package ru.ryabtsev.se.server.event;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;

@Getter
public class ServerMessageEvent {

    @NotNull
    private final Socket socket;

    @NotNull
    private final String message;

    public ServerMessageEvent( @NotNull final Socket socket, @NotNull final String message) {
        this.socket = socket;
        this.message = message;
    }
}
