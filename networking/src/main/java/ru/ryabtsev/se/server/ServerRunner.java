package ru.ryabtsev.se.server;

import ru.ryabtsev.se.Connection;
import ru.ryabtsev.se.NetworkConfiguration;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Getter;
import lombok.SneakyThrows;


public class ServerRunner implements Server {

    private final NetworkConfiguration networkConfiguration;

    private final ExecutorService executorService;

    private final ConnectionService connectionService;

    private ServerSocket serverSocket;


    public ServerRunner( final NetworkConfiguration networkConfiguration, final ExecutorService executorService) {
        this.networkConfiguration = networkConfiguration;
        this.executorService = executorService;
        this.connectionService = new ConnectionServiceBean( this );
    }

    @Override
    @SneakyThrows
    public void run() {
        serverSocket = new ServerSocket( networkConfiguration.getPort() );
        run( new ServerTaskConnection( this ) );
    }

    @Override
    public void run( ServerTask task ) {
        executorService.submit( task );
    }

    @Override
    public void add(final Socket socket) {
        connectionService.add( socket );
    }

    @Override
    public void remove(final Socket socket) {
        connectionService.remove( socket );
    }

    @Override
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public List<Connection> connections() {
        return connectionService.connections();
    }

    @Override
    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public NetworkConfiguration getNetworkConfiguration() {
        return networkConfiguration;
    }

}
