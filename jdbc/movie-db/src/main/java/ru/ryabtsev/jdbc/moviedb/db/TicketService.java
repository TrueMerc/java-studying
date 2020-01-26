package ru.ryabtsev.jdbc.moviedb.db;

public interface TicketService {

    /**
     * Adds new ticket for session with given identifier.
     * @param sessionId session identifier.
     */
    void addTicket(Integer sessionId);
}
