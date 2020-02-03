package ru.ryabtsev.jdbc.moviedb.configs;

import lombok.Getter;

@Getter
public class DatabaseConnectionConfiguration {

    private ConnectionConfiguration connectionConfiguration;
    private DatabaseConfiguration databaseConfiguration;
    private UserConfiguration userConfiguration;

    public DatabaseConnectionConfiguration(ConnectionConfiguration connectionConfiguration,
                                           DatabaseConfiguration databaseConfiguration,
                                           UserConfiguration userConfiguration
                                          )
    {
        this.connectionConfiguration = connectionConfiguration;
        this.databaseConfiguration = databaseConfiguration;
        this.userConfiguration = userConfiguration;
    }
}
