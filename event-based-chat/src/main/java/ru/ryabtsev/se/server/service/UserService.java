package ru.ryabtsev.se.server.service;

import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.User;

/**
 * Manages user accounts on server side.
 */
public interface UserService {

    @Nullable
    User find(@Nullable String login);

    boolean check(@Nullable String login, @Nullable String password);

    boolean registry(@Nullable String login, @Nullable String password);

    boolean exists(@Nullable String login);

    boolean setNickname(@Nullable String login, @Nullable String nick);

    boolean setPassword(@Nullable String login, @Nullable String oldPassword, @Nullable String newPassword);
}
