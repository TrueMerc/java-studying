package ru.ryabtsev.se.client;

import ru.ryabtsev.se.User;
import ru.ryabtsev.se.api.Application;
import ru.ryabtsev.se.logging.Logable;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Client application interface.
 */
public interface Client extends Application, Logable {
    /**
     * Returns client authorization sign.
     */
    boolean isAuthorized();

    /**
     * Sets client authorization sign.
     * @param isAuthorized - authorization sign.
     */
    void setAuthorized(boolean isAuthorized);

    /**
     * Get client user data.
     * @return user data.
     */
    User getUser();

    /**
     * Sets current user data.
     * @param user - user data.
     */
    void setUser( final User user);

    /**
     * Receives message from server.
     * @return server message.
     */
    String receive() throws IOException;

    /**
     * Sends message to server.
     * @param message - outgoing message.
     */
    void send(final String message);

    /**
     * Stops application execution and releases all application resources.
     */
    void exit();
}
