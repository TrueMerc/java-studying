package ru.ryabtsev.se.server;

public abstract class ServerTask extends Thread {

    private final Server server;

    protected ServerTask( Server server ) {
        this.server = server;
    }

    protected Server getServer() {
        return server;
    }
}
