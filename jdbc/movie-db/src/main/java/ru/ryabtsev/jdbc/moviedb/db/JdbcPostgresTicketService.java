package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.entities.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostgresTicketService extends JdbcPostgresDatabaseInteraction implements TicketService {

    private static final int SESSION_ID_ID = 1;
    private static final String ADD_TICKET_SQL = "INSERT INTO movies.tickets(session_id) VALUES(?)";

    private static final String GET_ALL_TICKETS_SQL = "SELECT session_id FROM movies.tickets";


    public JdbcPostgresTicketService(DatabaseConnectionConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void addTicket(Integer sessionId) {
        try {
            PreparedStatement addTicketStatement = connection.prepareStatement(ADD_TICKET_SQL);
            addTicketStatement.setInt(SESSION_ID_ID, sessionId);
            addTicketStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> resultList = new ArrayList<>();
        try {
            PreparedStatement getAllStatement = connection.prepareStatement(GET_ALL_TICKETS_SQL);
            ResultSet resultSet = getAllStatement.executeQuery();
            while(resultSet.next()) {
                resultList.add(new Ticket(resultSet.getInt(SESSION_ID_ID)));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
