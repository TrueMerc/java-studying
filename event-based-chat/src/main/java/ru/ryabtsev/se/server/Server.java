package ru.ryabtsev.se.server;

import lombok.NonNull;
import ru.ryabtsev.se.api.Application;

import java.net.ServerSocket;

/**
 * Server application class.
 */
public interface Server extends Application {

    /**
     * Returns server socket.
     * @return Server socket.
     */

    @NonNull
    ServerSocket getServerSocket();

    /**
     * Starts server application execution.
     */
    void run();
}
