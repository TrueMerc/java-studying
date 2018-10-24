package ru.ryabtsev.se.client;

import ru.ryabtsev.se.api.Application;

import java.io.DataInputStream;

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
    String receive();

    /**
     * Returns client application input stream.
     * @return input stream.
     */
    DataInputStream getInputStream();

    /**
     * Stops application execution and releases all application resources.
     */
    void exit();
}
