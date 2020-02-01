package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.ConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.UserConfiguration;

import java.sql.*;

/**
 * Provides database service implementation via JDBC for Postgres SQL.
 */
public class JdbcPostgresDatabaseService extends JdbcPostgresDatabaseInteraction implements DatabaseService {

    public JdbcPostgresDatabaseService(DatabaseConnectionConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void connect() throws Exception {
        super.connect();
    }

    @Override
    public void createSchema(String schemaName) throws Exception {
        connection.prepareStatement("CREATE SCHEMA " + schemaName + ';').executeUpdate();
    }

    @Override
    public void disconnect() throws Exception {
        super.disconnect();
    }

    @Override
    public boolean isTableExist(String tableName) {
        return isTableExist(null, tableName);
    }

    @Override
    public boolean isTableExist(String schemaName, String tableName) {
        boolean result = false;
        try {
            final ResultSet resultSet = connection.getMetaData().getTables(null, schemaName, tableName, null);
            result = resultSet.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createTable(String schemaName, String tableName, String columnsDescription) {
        try {
            final String statementString = formCreateTableStatement(schemaName, tableName, columnsDescription);
            connection.prepareStatement(statementString).executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String formCreateTableStatement(String schemaName, String tableName, String columnsDescription) {
        return "CREATE TABLE IF NOT EXISTS " + schemaName + '.' + tableName + '(' + columnsDescription + ')';
    }

    @Override
    public boolean isSchemaExist(String schemaName) {
        boolean result = false;
        try {
            final ResultSet resultSet = connection.getMetaData().getSchemas(null, schemaName);
            result = resultSet.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
