package ru.ryabtsev.jdbc.moviedb;

import lombok.SneakyThrows;
import ru.ryabtsev.jdbc.moviedb.configs.ConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.UserConfiguration;
import ru.ryabtsev.jdbc.moviedb.db.*;
import ru.ryabtsev.jdbc.moviedb.entities.Film;
import ru.ryabtsev.jdbc.moviedb.entities.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Implements the fourth homework of 'Coding Interview' course
 */
public class MovieDatabaseApplication
{
    private static final int FILMS_NUMBER = 5;
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_TYPE = "postgresql";
    private static final String DATABASE_NAME = "movies";
    private static final String DATABASE_HOST = "localhost";
    private static final String DATABASE_USERNAME = "admin";
    private static final String DATABASE_PASSWORD = "admin";
    private static final int DATABASE_PORT = 5432;

    private static final String DEFAULT_SCHEMA_NAME = "movies";
    private static final String[] DEFAULT_TABLE_NAMES = {"films", "schedule", "tickets" };
    private static final String[] DEFAULT_TABLE_COLUMNS = {
        "id SERIAL NOT NULL PRIMARY KEY, " +
        "title varchar(60) NOT NULL UNIQUE, " +
        "duration integer NOT NULL",

        "id SERIAL NOT NULL PRIMARY KEY, " +
        "film_id integer NOT NULL, " +
        "date_time timestamp NOT NULL, " +
        "price integer NOT NULL, " +
        "FOREIGN KEY(film_id) REFERENCES movies.films(id)",

        "id SERIAL NOT NULL PRIMARY KEY, " +
        "session_id integer NOT NULL, " +
        "FOREIGN KEY(session_id) REFERENCES movies.schedule(id)"
    };


    @SneakyThrows
    public static void main( String[] args )
    {
        createDatabaseIfNotExists();

        FilmsService filmsService = new JdbcPostgresFilmsService(getConfiguration());
        filmsService.connect();

        List<Film> films = filmsService.getAll();
        if(films.isEmpty()) {
            for (int i = 0; i < FILMS_NUMBER; ++i) {
                filmsService.addFilm("Film " + i, 30 * (i % 3) + 60);
            }
        }

        filmsService.disconnect();

        LocalDate today = LocalDate.now();

        ScheduleService scheduleService = new JdbcPostgresScheduleService(getConfiguration());
        scheduleService.connect();

        List<Session> sessions = scheduleService.getAll();
        if(sessions.isEmpty()) {
            for (int i = 0; i < FILMS_NUMBER; ++i) {
                LocalDateTime startTime = LocalDateTime.of(
                        today,
                        LocalTime.of(14 + i + i % 2, 30 * ((i + 1) % 2), 0)
                );
                float price = .5f * (float) (i + 2);
                scheduleService.addSession(startTime, price, i + 1);
            }
        }

        List<Session> intersections = scheduleService.listOfIntersections();

        scheduleService.disconnect();
    }

    @SneakyThrows
    private static void createDatabaseIfNotExists() {
        DatabaseService databaseService = new JdbcPostgresDatabaseService(getConfiguration());
        databaseService.connect();
        System.out.println("Connection established.");

        if(databaseService.isSchemaExist(DEFAULT_SCHEMA_NAME)) {
            System.out.println("Schema does exist.");
        }
        else {
            System.out.println("Schema does not exist.");
            databaseService.createSchema(DEFAULT_SCHEMA_NAME);
        }

        for(int i = 0; i < DEFAULT_TABLE_NAMES.length; ++i) {
            if(!databaseService.isTableExist(DEFAULT_TABLE_NAMES[i])) {
                databaseService.createTable(DEFAULT_SCHEMA_NAME, DEFAULT_TABLE_NAMES[i], DEFAULT_TABLE_COLUMNS[i]);
            }
        }
        databaseService.disconnect();
    }

    private static DatabaseConnectionConfiguration getConfiguration() {
        final ConnectionConfiguration connectionConfig = new ConnectionConfiguration(DATABASE_HOST, DATABASE_PORT);
        final DatabaseConfiguration databaseConfig =
                new DatabaseConfiguration(DATABASE_DRIVER, DATABASE_TYPE, DATABASE_NAME);
        final UserConfiguration userConfig = new UserConfiguration(DATABASE_USERNAME, DATABASE_PASSWORD);
        return new DatabaseConnectionConfiguration(connectionConfig, databaseConfig, userConfig);
    }
}
