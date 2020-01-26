package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.ConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.UserConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcPostgresDatabaseInteraction {
    protected DatabaseConnectionConfiguration configuration;
    protected Connection connection;

    JdbcPostgresDatabaseInteraction(DatabaseConnectionConfiguration configuration) {
        this.configuration = configuration;
    }

    public void connect() throws Exception {
        Class.forName(configuration.getDatabaseConfiguration().getDriverName());
        final UserConfiguration user = configuration.getUserConfiguration();
        connection = DriverManager.getConnection(getUrl(), user.getLogin(), user.getPassword());
    }

    private String getUrl() {
        final DatabaseConfiguration dbConfig = configuration.getDatabaseConfiguration();
        final ConnectionConfiguration connectConfig = configuration.getConnectionConfiguration();

        return "jdbc:" + dbConfig.getDatabaseType() + "://"
                + connectConfig.getHost() + ':' + connectConfig.getPort() + '/' + dbConfig.getDatabaseName();
    }

}
