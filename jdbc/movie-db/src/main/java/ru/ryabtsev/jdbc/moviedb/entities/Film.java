package ru.ryabtsev.jdbc.moviedb.entities;

public class Film {
    private final String title;
    private final int duration;

    public Film(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String toString() {
        return "Title: " + title + ", duration: " + duration;
    }
}
