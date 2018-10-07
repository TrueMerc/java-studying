package ru.ryabtsev.se;

import ru.ryabtsev.se.client.ClientApplication;
import ru.ryabtsev.se.server.ConsoleServer;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;


/**
 * Simple 'client-server chat' application main class.
 */
public class NetworkChatApplication
{
    /**
     * Main method.
     * @param args - command line arguments.
     */
    public static void main( String[] args )
    {
        SeContainerInitializer.newInstance().addPackages(NetworkChatApplication.class).initialize();
        final NetworkConfiguration networkConfiguration = CDI.current().select(NetworkConfiguration.class).get();
        final Application application = initializeApplication( args );
        if( application == null ) {
            printHelp();
            return;
        }
        application.run();
    }

    /**
     * Returns application instance.
     * @param args - command line arguments.
     * @return application instance (client or server).
     */
    private static Application initializeApplication( String[] args ) {
        if( args == null || args.length == 0 ) {
            return new ClientApplication();
        }
        else if( args.length == 1 && "server".equals( args[0] ) ) {
            return new ConsoleServer();
        }
        return null;
    }

    /**
     * Prints help message.
     */
    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println();
        System.out.println("simple-chat [server]");
        System.out.println();
        System.out.println("Runs chat client application or char server application (if server option was set)");
    }

}
