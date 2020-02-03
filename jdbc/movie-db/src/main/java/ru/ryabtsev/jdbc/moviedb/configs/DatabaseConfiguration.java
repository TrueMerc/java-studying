package ru.ryabtsev.jdbc.moviedb.configs;

import lombok.Getter;

/**
 * Implements database settings configuration.
 */
@Getter
public class DatabaseConfiguration {

    private final String driverName;
    private final String databaseType;
    private final String databaseName;

    public DatabaseConfiguration(String driverName, String databaseType, String databaseName) {
        this.driverName = driverName;
        this.databaseType = databaseType;
        this.databaseName = databaseName;
    }
}
