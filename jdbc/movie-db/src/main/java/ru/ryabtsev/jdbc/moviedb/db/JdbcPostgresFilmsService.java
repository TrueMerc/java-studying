package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.configs.DatabaseConnectionConfiguration;
import ru.ryabtsev.jdbc.moviedb.entities.Film;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostgresFilmsService extends JdbcPostgresDatabaseInteraction implements FilmsService {

    private static final int FILM_TITLE_ID = 1;
    private static final int FILM_DURATION_ID = 2;
    private static final String ADD_FILM_SQL = "INSERT INTO movies.films(title, duration) VALUES(?, ?)";
    private static final String FIND_ALL_FILMS = "SELECT * FROM movies.films";

    private PreparedStatement addFilmStatement;

    public JdbcPostgresFilmsService(DatabaseConnectionConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void connect() throws Exception {
        super.connect();
    }

    @Override
    public void disconnect() throws Exception {
        super.disconnect();
    }

    @Override
    public void addFilm(String title, Integer duration) {
        try {
            addFilmStatement = connection.prepareStatement(ADD_FILM_SQL);
            addFilmStatement.setString(FILM_TITLE_ID, title);
            addFilmStatement.setInt(FILM_DURATION_ID, duration);
            addFilmStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Film> getAll() {
        List<Film> resultList = new ArrayList<>();
        try {
            PreparedStatement findAllStatement = connection.prepareStatement(FIND_ALL_FILMS);
            ResultSet result = findAllStatement.executeQuery();

            while(result.next()) {
                String title = result.getString(FILM_TITLE_ID + 1);
                int duration = result.getInt(FILM_DURATION_ID + 1);
                resultList.add(new Film(title, duration));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
