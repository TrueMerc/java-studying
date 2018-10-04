package ru.ryabtsev.se.server;

import ru.ryabtsev.se.NetworkConfiguration;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

import lombok.SneakyThrows;


public class ConsoleServer implements Server {

    private final NetworkConfiguration networkConfiguration;

    private final ExecutorService executorService;

    private final Connections connections;

    private ServerSocket serverSocket;


    public ConsoleServer(final NetworkConfiguration networkConfiguration, final ExecutorService executorService) {
        this.networkConfiguration = networkConfiguration;
        this.executorService = executorService;
        this.connections = new Connections();
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
        connections.add( socket );
    }

    @Override
    public void remove(final Socket socket) {
        connections.remove( socket );
    }

    @Override
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public List<Connection> connections() {
        return connections.connections();
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
