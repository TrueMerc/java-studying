package ru.ryabtsev.jdbc.moviedb.configs;

import lombok.Getter;

/**
 * Implements user settings configuration.
 */
@Getter
public class UserConfiguration {

    private String login;
    private String password;

    public UserConfiguration(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
