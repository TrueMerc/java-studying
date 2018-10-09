package ru.ryabtsev.se.cdi;

import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Getter
public class Singletone {
    private final Integer field = 1;
}
