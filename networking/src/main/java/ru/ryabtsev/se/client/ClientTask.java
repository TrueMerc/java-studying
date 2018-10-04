package ru.ryabtsev.se.client;

/**
 * Base class for all task classes which executes on client side.
 */
public abstract class ClientTask extends Thread {

    protected Client client;

    protected ClientTask( final Client client ) {
        this.client = client;
    }
}
