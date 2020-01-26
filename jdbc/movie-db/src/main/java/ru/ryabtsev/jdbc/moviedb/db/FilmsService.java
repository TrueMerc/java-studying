package ru.ryabtsev.jdbc.moviedb.db;

/**
 * Provides interface for interaction with database film entities.
 */
public interface FilmsService {
    void addFilm(String title, Integer duration);
}
