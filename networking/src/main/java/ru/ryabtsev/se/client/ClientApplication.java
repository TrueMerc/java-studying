package ru.ryabtsev.se.client;

import ru.ryabtsev.se.Application;
import ru.ryabtsev.se.NetworkConfiguration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApplication implements Application {

    private final static int MAXIMUM_THREADS = 3;

    private final NetworkConfiguration networkConfiguration;

    private final ExecutorService executorService;

    private final Client client;

    public ClientApplication() {
        networkConfiguration = new NetworkConfiguration();
        executorService = Executors.newFixedThreadPool( MAXIMUM_THREADS );
        client = new ConsoleClient( networkConfiguration, executorService );
    }

    @Override
    public void run() {
        client.run();
    }

    public static void main( String[] args ) {
        final Application application = new ClientApplication();
        application.run();
    }
}
