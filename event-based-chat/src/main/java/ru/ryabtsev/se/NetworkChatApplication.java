package ru.ryabtsev.se;

import ru.ryabtsev.se.api.Application;
import ru.ryabtsev.se.client.ClientBean;
import ru.ryabtsev.se.server.ServerBean;

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
        SeContainerInitializer.newInstance().addPackages(NetworkChatApplication.class).initialize();

        if( args == null || args.length == 0 ) {
            return CDI.current().select( ClientBean.class ).get();
        }
        else if( args.length == 1 && "server".equals( args[0] ) ) {
            return CDI.current().select( ServerBean.class ).get();
        }
        return CDI.current().select( ServerBean.class ).get();
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
