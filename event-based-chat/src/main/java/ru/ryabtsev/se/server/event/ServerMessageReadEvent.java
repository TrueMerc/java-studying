package ru.ryabtsev.se.server.event;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;

@Getter
public class ServerMessageReadEvent  {

    private final Socket socket;

    public ServerMessageReadEvent(@NotNull Socket socket) {
        this.socket = socket;
    }
}
