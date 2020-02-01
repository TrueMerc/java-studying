package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcPostgresTicketService extends JdbcPostgresDatabaseInteraction implements TicketService {

    private static final int SESSION_ID_ID = 1;
    private static final String ADD_TICKET_SQL = "INSERT INTO movies.tickets(session_id) VALUES(?)";
    private PreparedStatement addTicketStatement;

    JdbcPostgresTicketService(DatabaseConnectionConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void addTicket(Integer sessionId) {
        try {
            addTicketStatement = connection.prepareStatement(ADD_TICKET_SQL);
            addTicketStatement.setInt(SESSION_ID_ID, sessionId);
            addTicketStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
