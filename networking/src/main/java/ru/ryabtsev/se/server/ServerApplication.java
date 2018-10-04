package ru.ryabtsev.se.server;

import ru.ryabtsev.se.Application;
import ru.ryabtsev.se.NetworkConfiguration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApplication implements Application {

    private final NetworkConfiguration networkConfiguration;

    private final ExecutorService executorService;

    private final Server server;

    public ServerApplication() {
        networkConfiguration = new NetworkConfiguration();
        //executorService = Executors.newFixedThreadPool( networkConfiguration.getThreadsNumber() );
        executorService = Executors.newCachedThreadPool();
        server = new ServerRunner( networkConfiguration, executorService );
    }

    @Override
    public void run() {
        server.run();
    }

    public static void main( String[] args ) {
        final ServerApplication application = new ServerApplication();
        application.run();
    }
}
