package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.entities.Session;

import java.time.LocalDateTime;
import java.util.List;

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

    List<Session> listOfIntersections();

    List<Session> getAll();
}
