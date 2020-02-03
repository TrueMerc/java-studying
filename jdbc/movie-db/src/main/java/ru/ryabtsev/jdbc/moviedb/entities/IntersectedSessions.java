package ru.ryabtsev.jdbc.moviedb.entities;

import lombok.Getter;

@Getter
public class IntersectedSessions {

    private final Session first;
    private final Session second;

    public IntersectedSessions(Session first, Session second) {
        this.first = first;
        this.second = second;
    }
}
