package ru.ryabtsev.se.client;

import lombok.SneakyThrows;

public abstract class ClientTask extends Thread {

    protected Client client;

    protected ClientTask( final Client client ) {
        this.client = client;
    }
}
