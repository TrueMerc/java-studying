package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.entities.Ticket;

import java.util.List;

/**
 * Provides interface for interaction with database ticket entities.
 */
public interface TicketService {

    /**
     * Adds new ticket for session with given identifier.
     * @param sessionId session identifier.
     */
    void addTicket(Integer sessionId);

    void connect() throws Exception;

    void disconnect() throws Exception;

    List<Ticket> getAll();
}
