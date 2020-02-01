package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JdbcPostgresScheduleService extends JdbcPostgresDatabaseInteraction implements ScheduleService {

    private static final int FILM_ID_ID = 1;
    private static final int DATE_TIME_ID = 2;
    private static final int PRICE_ID = 3;
    private static final String ADD_SESSION_SQL =
            "INSERT INTO movies.schedule(film_id, date_time, price) VALUES(?, ?, ?)";
    private PreparedStatement addSessionStatement;

    public JdbcPostgresScheduleService(DatabaseConnectionConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void addSession(LocalDateTime dateTime, float price, int filmId) {
        try {
            addSessionStatement = connection.prepareStatement(ADD_SESSION_SQL);
            addSessionStatement.setInt(FILM_ID_ID, filmId);
            addSessionStatement.setTimestamp(DATE_TIME_ID, Timestamp.valueOf(dateTime));
            addSessionStatement.setFloat(PRICE_ID, price);
            addSessionStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
