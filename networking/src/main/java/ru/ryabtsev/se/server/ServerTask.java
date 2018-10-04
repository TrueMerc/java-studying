package ru.ryabtsev.se.server;

/**
 * Base class for all task classes which executes on server side.
 */
public abstract class ServerTask extends Thread {

    private final Server server;

    protected ServerTask( Server server ) {
        this.server = server;
    }

    protected Server getServer() {
        return server;
    }
}
