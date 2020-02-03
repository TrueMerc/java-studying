package ru.ryabtsev.jdbc.moviedb;

import lombok.SneakyThrows;
import ru.ryabtsev.jdbc.moviedb.configs.ConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.configs.UserConfiguration;
import ru.ryabtsev.jdbc.moviedb.db.*;
import ru.ryabtsev.jdbc.moviedb.entities.Film;
import ru.ryabtsev.jdbc.moviedb.entities.IntersectedSessions;
import ru.ryabtsev.jdbc.moviedb.entities.Session;
import ru.ryabtsev.jdbc.moviedb.entities.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.IntUnaryOperator;

/**
 * Implements the fourth homework of 'Coding Interview' course
 */
public class MovieDatabaseApplication
{
    private static final int FILMS_NUMBER = 6;
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
        createFilmsIfNotExists();
        createScheduleIfNotExists();
        createTicketsIfNotExists();

        FilmsService filmsService = new JdbcPostgresFilmsService(getConfiguration());
        filmsService.connect();
        ScheduleService scheduleService = new JdbcPostgresScheduleService(getConfiguration(), filmsService);
        scheduleService.connect();

        List<IntersectedSessions> intersections = scheduleService.listOfIntersections();
        printIntersectedSessionsList(intersections);

        filmsService.disconnect();
        scheduleService.disconnect();
    }

    @SneakyThrows
    private static void createDatabaseIfNotExists() {
        DatabaseService databaseService = new JdbcPostgresDatabaseService(getConfiguration());
        databaseService.connect();

        for(int i = 0; i < DEFAULT_TABLE_NAMES.length; ++i) {
            if(!databaseService.isTableExist(DEFAULT_TABLE_NAMES[i])) {
                databaseService.createTable(DEFAULT_SCHEMA_NAME, DEFAULT_TABLE_NAMES[i], DEFAULT_TABLE_COLUMNS[i]);
            }
        }
        databaseService.disconnect();
    }

    @SneakyThrows
    private static void createFilmsIfNotExists() {

        final String[] filmNames = {
                "Full metal jacket", "Filth", "Pulp fiction",
                "Snatch", "Platoon", "Red state"
        };

        FilmsService filmsService = new JdbcPostgresFilmsService(getConfiguration());
        filmsService.connect();

        IntUnaryOperator calculateDuration = (int index) -> {
            final int baseDurationInMinutes = 60;
            final int period = 3;
            final int additionalDurationInMinutes = 30;

            return baseDurationInMinutes + additionalDurationInMinutes * (index % 3);
        };

        List<Film> films = filmsService.getAll();
        if(films.isEmpty()) {
            for (int i = 0; i < FILMS_NUMBER; ++i) {
                filmsService.addFilm(filmNames[i], calculateDuration.applyAsInt(i));
            }
        }

        filmsService.disconnect();
    }

    @SneakyThrows
    private static void createScheduleIfNotExists() {
        FilmsService filmsService = new JdbcPostgresFilmsService(getConfiguration());
        filmsService.connect();
        ScheduleService scheduleService = new JdbcPostgresScheduleService(getConfiguration(), filmsService);
        scheduleService.connect();

        List<Session> sessions = scheduleService.getAll();
        if(sessions.isEmpty()) {
            List<Film> films = filmsService.getAll();
            LocalDate today = LocalDate.now();
            for (int i = 0; i < films.size() - 1; ++i) {
                LocalDateTime startTime = LocalDateTime.of(
                        today,
                        LocalTime.of(14 + i + i % 2, 30 * ((i + 1) % 2), 0)
                );
                final float price = .5f * (float) (i + 2);
                scheduleService.addSession(startTime, price, i + 1);
            }
            final LocalTime lastSessionTime = LocalTime.of(22, 0, 0);
            scheduleService.addSession(LocalDateTime.of(today, lastSessionTime) ,5, films.size() - 1);
            final LocalTime firstSessionTime = LocalTime.of( 9, 0, 0);
            scheduleService.addSession(LocalDateTime.of(today, firstSessionTime), 5, 1);
        }
    }

    @SneakyThrows
    private static void createTicketsIfNotExists() {
        TicketService ticketService = new JdbcPostgresTicketService(getConfiguration());
        ticketService.connect();

        List<Ticket> tickets = ticketService.getAll();
        if(tickets.isEmpty()) {
            FilmsService filmsService = new JdbcPostgresFilmsService(getConfiguration());
            filmsService.connect();
            ScheduleService scheduleService = new JdbcPostgresScheduleService(getConfiguration(), filmsService);
            scheduleService.connect();
            List<Session> sessions = scheduleService.getAll();

            for(int i = 0; i < sessions.size(); ++i) {
                for(int count = 0; count < 3; ++count) {
                    ticketService.addTicket(i + 1);
                }
            }

            filmsService.disconnect();
            scheduleService.disconnect();
        }

        ticketService.disconnect();
    }

    private static DatabaseConnectionConfiguration getConfiguration() {
        final ConnectionConfiguration connectionConfig = new ConnectionConfiguration(DATABASE_HOST, DATABASE_PORT);
        final DatabaseConfiguration databaseConfig =
                new DatabaseConfiguration(DATABASE_DRIVER, DATABASE_TYPE, DATABASE_NAME);
        final UserConfiguration userConfig = new UserConfiguration(DATABASE_USERNAME, DATABASE_PASSWORD);
        return new DatabaseConnectionConfiguration(connectionConfig, databaseConfig, userConfig);
    }

    private static void printIntersectedSessionsList(List<IntersectedSessions> intersectedSessions) {
        for(IntersectedSessions sessions : intersectedSessions) {
            System.out.println(sessions.getFirst() + " intersects with " + sessions.getSecond());
        }
    }
}
