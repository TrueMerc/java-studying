package ru.ryabtsev.se.client;

import ru.ryabtsev.se.api.Application;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Client application interface.
 */
public interface Client extends Application {

    /**
     * Sends message to server.
     * @param message - outgoing message.
     */
    void send(final String message);

    /**
     * Receives message from server.
     * @return server message.
     */
    String receive() throws IOException;

    /**
     * Stops application execution and releases all application resources.
     */
    void exit();
}
