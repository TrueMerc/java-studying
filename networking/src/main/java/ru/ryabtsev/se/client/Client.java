package ru.ryabtsev.se.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Client extends Runnable {

    void run( ClientTask task );

    DataInputStream getInputStream();

    DataOutputStream getOutputStream();

    void exit();

    void send( String message );
}
