package ru.ryabtsev.jdbc.moviedb.db;

import ru.ryabtsev.jdbc.moviedb.entities.Film;

import java.util.List;

/**
 * Provides interface for interaction with database film entities.
 */
public interface FilmsService {
    void addFilm(String title, Integer duration);

    List<Film> getAll();

    Film get(long id);

    List<Film> get(String name);

    void connect() throws Exception;

    void disconnect() throws Exception;
}
