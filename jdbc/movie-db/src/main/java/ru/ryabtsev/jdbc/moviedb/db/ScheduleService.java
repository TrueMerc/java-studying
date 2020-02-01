package ru.ryabtsev.jdbc.moviedb.db;

import java.time.LocalDateTime;

/**
 * Provides interface for interaction with database schedule table.
 */
public interface ScheduleService {

    /**
     * Adds new session into database.
     */
    void addSession(LocalDateTime dateTime, float price, int filmId);

    void connect() throws Exception;

    void disconnect() throws Exception;
}
