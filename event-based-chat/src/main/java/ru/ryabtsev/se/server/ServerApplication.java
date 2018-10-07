package ru.ryabtsev.se.server;

import lombok.NonNull;
import ru.ryabtsev.se.Application;
import ru.ryabtsev.se.NetworkConfiguration;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
