package ru.ryabtsev.jdbc.moviedb.db;

/**
 * Provides interface for database service which responses for DDL operations with database.
 */
public interface DatabaseService {

    /**
     * Connects to database server.
     */
    void connect() throws Exception;

    /**
     * Creates database schema if it doesn't exist.
     */
    void createSchema(String schemaName) throws Exception;

    /**
     * Creates database schema table if it doesn't exist.
     */
    void createTable(String schemaName, String tableName, String columnsDescription);

    /**
     * Disconnects from database server.
     */
    void disconnect() throws Exception;

    /**
     * Checks database schema existence.
     * @return true - if database schema already exists, and false in other case.
     */
    boolean isSchemaExist(String schemaName);

    /**
     * Checks database schema table existence.
     * @return true - if database schema table already exists, and false in other case.
     */
    boolean isTableExist(String tableName);

    /**
     * Checks database schema table existence.
     * @return true - if database schema table already exists, and false in other case.
     */
    boolean isTableExist(String schemaName, String tableName);
}
