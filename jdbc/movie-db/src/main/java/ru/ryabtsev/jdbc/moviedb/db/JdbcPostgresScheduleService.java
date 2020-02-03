package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.entities.Film;
import ru.ryabtsev.jdbc.moviedb.entities.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostgresScheduleService extends JdbcPostgresDatabaseInteraction implements ScheduleService {

    private static final int FILM_ID_ID = 1;
    private static final int DATE_TIME_ID = 2;
    private static final int PRICE_ID = 3;
    private static final String ADD_SESSION_SQL =
            "INSERT INTO movies.schedule(film_id, date_time, price) VALUES(?, ?, ?)";
    private static final String FIND_ALL_INTERSECTIONS_SQL =
            "SELECT s1.film_id, s1.date_time, f1.duration, s2.film_id, s2.date_time, f2.duration " +
            "FROM " +
                "movies.schedule s1 INNER JOIN movies.films f1 ON s1.film_id = f1.id, " +
                "movies.schedule s2 INNER JOIN movies.films f2 ON s2.film_id = f2.id " +
            "WHERE " +
                "s1.id < s2.id AND s2.date_time < s1.date_time + (f1.duration::TEXT||'minute')::INTERVAL " +
            "ORDER BY s1.date_time";
    private static final String GET_ALL_SESSIONS_SQL = "SELECT * FROM movies.schedule";

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

    @Override
    public List<Session> listOfIntersections() {
        List<Session> resultList = new ArrayList<>();
        try {
            PreparedStatement findAllIntersections = connection.prepareStatement(FIND_ALL_INTERSECTIONS_SQL);
            ResultSet result = findAllIntersections.executeQuery();
            while(result.next()) {
                Film film = new Film("name", 0);
                Timestamp timestamp = result.getTimestamp(2);
                float price = 0;
                resultList.add(new Session(film, timestamp.toLocalDateTime(), price));
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Session> getAll() {
        List<Session> resultList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_SESSIONS_SQL);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Film film = new Film("name", 0);
                Timestamp timestamp = result.getTimestamp(DATE_TIME_ID + 1);
                float price = 0;
                resultList.add(new Session(film, timestamp.toLocalDateTime(), price));
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return resultList;
    }
}
