package ru.ryabtsev.se.server.event;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;

@Getter
public class ServerMessageReadEvent extends ServerMessageEvent {
    public ServerMessageReadEvent(@NotNull Socket socket, @NotNull String message) {
        super(socket, message);
    }
}
