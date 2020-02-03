package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.entities.Film;
import ru.ryabtsev.jdbc.moviedb.entities.IntersectedSessions;
import ru.ryabtsev.jdbc.moviedb.entities.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostgresScheduleService extends JdbcPostgresDatabaseInteraction implements ScheduleService {
    // 'Add new session' query parameters.
    private static final String ADD_SESSION_SQL =
            "INSERT INTO movies.schedule(film_id, date_time, price) VALUES(?, ?, ?)";
    private static final int ADD_SESSION_SQL_FILM_ID_FIELD = 1;
    private static final int ADD_SESSION_SQL_DATE_TIME_FIELD = 2;
    private static final int ADD_SESSION_SQL_PRICE_FIELD = 3;

    // 'Find all time intersections' query parameters.
    private static final String FIND_ALL_INTERSECTIONS_SQL =
            "SELECT s1.film_id, s1.date_time, s1.price, s2.film_id, s2.date_time, s2.price " +
            "FROM " +
                "movies.schedule s1 INNER JOIN movies.films f1 ON s1.film_id = f1.id, " +
                "movies.schedule s2 INNER JOIN movies.films f2 ON s2.film_id = f2.id " +
            "WHERE " +
                "s1.id < s2.id AND s2.date_time < s1.date_time + (f1.duration::TEXT||'minute')::INTERVAL " +
            "ORDER BY s1.date_time";
    private static final int FIND_ALL_INTERSECTIONS_SQL_FIRST_FILM_ID_FIELD = 1;
    private static final int FIND_ALL_INTERSECTIONS_SQL_FIRST_DATE_TIME_FIELD = 2;
    private static final int FIND_ALL_INTERSECTIONS_SQL_FIRST_PRICE_FIELD = 3;
    private static final int FIND_ALL_INTERSECTIONS_SQL_SECOND_FILM_ID_FIELD = 4;
    private static final int FIND_ALL_INTERSECTIONS_SQL_SECOND_DATE_TIME_FIELD = 5;
    private static final int FIND_ALL_INTERSECTIONS_SQL_SECOND_PRICE_FIELD = 6;

    // 'Find too long breaks' query parameters.
    private static final String FIND_TO_LONG_BREAKS_SQL = "";

    // 'Get all sessions' query parameters.
    private static final String GET_ALL_SESSIONS_SQL = "SELECT film_id, date_time, price FROM movies.schedule";
    private static final int GET_ALL_SESSIONS_SQL_FILM_ID_FIELD = 1;
    private static final int GET_ALL_SESSIONS_SQL_DATE_TIME_FIELD = 2;
    private static final int GET_ALL_SESSIONS_SQL_PRICE_FIELD = 3;

    private final FilmsService filmsService;

    public JdbcPostgresScheduleService(DatabaseConnectionConfiguration configuration, FilmsService filmsService) {
        super(configuration);
        this.filmsService = filmsService;
    }

    @Override
    public void addSession(LocalDateTime dateTime, float price, int filmId) {
        try {
            PreparedStatement addSessionStatement = connection.prepareStatement(ADD_SESSION_SQL);
            addSessionStatement.setInt(ADD_SESSION_SQL_FILM_ID_FIELD, filmId);
            addSessionStatement.setTimestamp(ADD_SESSION_SQL_DATE_TIME_FIELD, Timestamp.valueOf(dateTime));
            addSessionStatement.setFloat(ADD_SESSION_SQL_PRICE_FIELD, price);
            addSessionStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IntersectedSessions> listOfIntersections() {
        final List<IntersectedSessions> resultList = new ArrayList<>();
        try {
            final PreparedStatement findAllIntersections = connection.prepareStatement(FIND_ALL_INTERSECTIONS_SQL);
            final ResultSet result = findAllIntersections.executeQuery();
            while(result.next()) {
                final Film firstFilm = filmsService.get(result.getInt(FIND_ALL_INTERSECTIONS_SQL_FIRST_FILM_ID_FIELD));
                final Timestamp firstTimeStamp = result.getTimestamp(FIND_ALL_INTERSECTIONS_SQL_FIRST_DATE_TIME_FIELD);
                final float firstPrice = result.getFloat(FIND_ALL_INTERSECTIONS_SQL_FIRST_PRICE_FIELD);
                final Session firstSession = new Session(firstFilm, firstTimeStamp.toLocalDateTime(), firstPrice);
                final Film secondFilm = filmsService.get(result.getInt(FIND_ALL_INTERSECTIONS_SQL_SECOND_FILM_ID_FIELD));
                final Timestamp secondTimeStamp = result.getTimestamp(FIND_ALL_INTERSECTIONS_SQL_SECOND_DATE_TIME_FIELD);
                final float secondPrice = result.getFloat(FIND_ALL_INTERSECTIONS_SQL_SECOND_PRICE_FIELD);
                final Session secondSession = new Session(secondFilm, secondTimeStamp.toLocalDateTime(), secondPrice);

                resultList.add(new IntersectedSessions(firstSession, secondSession));
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
                Film film = filmsService.get(result.getInt(GET_ALL_SESSIONS_SQL_FILM_ID_FIELD));
                Timestamp timestamp = result.getTimestamp(GET_ALL_SESSIONS_SQL_DATE_TIME_FIELD);
                float price = result.getFloat(GET_ALL_SESSIONS_SQL_PRICE_FIELD);
                resultList.add(new Session(film, timestamp.toLocalDateTime(), price));
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return resultList;
    }
}
