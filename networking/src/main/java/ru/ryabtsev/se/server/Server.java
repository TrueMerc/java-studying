package ru.ryabtsev.se.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

import ru.ryabtsev.se.Connection;
import ru.ryabtsev.se.NetworkConfiguration;

public interface Server extends Runnable {

    ExecutorService getExecutorService();

    NetworkConfiguration getNetworkConfiguration();

    ServerSocket getServerSocket();

    List<Connection> connections();

    void run( ServerTask task );

    void add( Socket socket );

    void remove( Socket socket );
}
