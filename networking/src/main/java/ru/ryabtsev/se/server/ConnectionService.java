package ru.ryabtsev.se.server;

import ru.ryabtsev.se.Connection;

import java.net.Socket;
import java.util.List;

public interface ConnectionService {

    void add( Socket socket );

    Connection get(Socket socket );

    void remove( Socket socket );

    List<Connection> connections();
}
