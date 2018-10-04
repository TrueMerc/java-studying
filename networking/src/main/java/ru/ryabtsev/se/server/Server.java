package ru.ryabtsev.se.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

import ru.ryabtsev.se.NetworkConfiguration;

public interface Server extends Runnable {

    /**
     * Returns executor service.
     * @return Executor service.
     */
    ExecutorService getExecutorService();


    /**
     * Returns network configuration.
     * @return Network configuration.
     */
    NetworkConfiguration getNetworkConfiguration();

    /**
     * Returns server socket.
     * @return Server socket.
     */
    ServerSocket getServerSocket();

    /**
     * Returns connection list.
     * @return List of established connections.
     */
    List<Connection> connections();

    /**
     * Starts new server task execution.
     * @param task - runnable task.
     */
    void run( ServerTask task );

    /**
     * Adds new connection to connection list.
     * @param socket - connection socket.
     */
    void addConnection( Socket socket );

    /**
     * Removes connection from connection list.
     * @param socket - connection socket.
     */
    void removeConnection( Socket socket );
}
