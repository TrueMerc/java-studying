package ru.ryabtsev.se.server.jdbc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ryabtsev.se.packets.User;

import java.util.UUID;

/**
 * Database entry object for users registration.
 */
@Getter
@NoArgsConstructor
public class UserRegistrationDTO {

    private String id;

    private String login;

    private String password;

    private String nickname;

    public UserRegistrationDTO( String login, String password, String nickname ) {
        id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }
}
