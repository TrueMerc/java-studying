package ru.ryabtsev.se.client;

import ru.ryabtsev.se.api.Application;

import java.io.DataInputStream;

/**
 * Client interface.
 */
public interface Client extends Application {

    void send(final String message);

    String receive();

    DataInputStream getIn();

    void exit();
}
