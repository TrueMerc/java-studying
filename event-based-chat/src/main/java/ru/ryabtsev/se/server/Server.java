package ru.ryabtsev.se.server;

import lombok.NonNull;
import ru.ryabtsev.se.application.Application;

import java.net.ServerSocket;

/**
 * Server application class.
 */
public interface ServerApplication extends Application {

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
