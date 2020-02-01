package ru.ryabtsev.jdbc.moviedb.entities;

import java.time.LocalDateTime;

public class Session {
    private final Film film;
    private final LocalDateTime dateTime;
    private final float price;

    public Session(Film film, LocalDateTime dateTime, float price) {
        this.film = film;
        this.dateTime = dateTime;
        this.price = price;
    }

    @Override
    public String toString() {
        return film + ", starts at " + dateTime + ", price: " + price;
    }
}
