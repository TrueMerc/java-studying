package ru.ryabtsev.jdbc.moviedb.configs;

import lombok.Getter;

/**
 * Implements connection settings configuration.
 */
@Getter
public class ConnectionConfiguration {

    private String host;
    private Integer port;

    public ConnectionConfiguration(String host, Integer port) {
        this.host = host;
        this.port = port;
    }
}
