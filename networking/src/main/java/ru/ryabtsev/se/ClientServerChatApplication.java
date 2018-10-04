package ru.ryabtsev.se;

import ru.ryabtsev.se.client.ClientApplication;
import ru.ryabtsev.se.server.ServerApplication;

/**
 * Simple 'client-server chat' application main class.
 */
public class ClientServerChatApplication
{
    /**
     * Main method.
     * @param args - command line arguments.
     */
    public static void main( String[] args )
    {
        final Application application = initializeApplication( args );
        application.run();
    }

    /**
     * Returns application instance.
     * @param args - command line arguments.
     * @return application instance (client or server).
     */
    private static Application initializeApplication( String[] args ) {
        // FIXME Move if condition to separate boolean function.
        if( args == null || args.length == 0 ) {
            return new ClientApplication();
        }
        // FIXME Move if condition to separate boolean function.
        else if( args.length == 1 || "server".equals( args[0] ) ) {
            return new ServerApplication();
        }
        return null;
    }

}
