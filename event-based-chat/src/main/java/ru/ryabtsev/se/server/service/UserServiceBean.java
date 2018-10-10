package ru.ryabtsev.se.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manages chat users.
 */
@NoArgsConstructor
@ApplicationScoped
public class UserServiceBean implements UserService {
    private Map<String, User> users = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        registry("admin", "admin" );
        registry("user", "user" );
    }

    public boolean registry(@NotNull final String login, @NotNull final String password) {
        if( exists( login ) ) {
            return false;
        }
        else {
            final User user = new User();
            user.setLogin( login );
            user.setPassword( password );
            users.put( login, user );
            return true;
        }
    }

    @Override
    public @Nullable User find(@Nullable String login) {
        return users.get( login );
    }

    public boolean check(@NotNull final String login, @NotNull final String password) {
        return  exists(login) && find(login).getPassword().equals( password ) ;
    }

    public boolean exists(@NotNull final String login) {
        return users.containsKey(login);
    }

    @Override
    public boolean setNickname(@Nullable String login, @Nullable String nickname) {
        final boolean result = exists(login);
        if( result ) {
            find(login).setNickname( nickname );
        }
        return result;
    }

    @Override
    public boolean setPassword(@Nullable String login, @Nullable String oldPassword, @Nullable String newPassword) {
        final boolean result = check( login, oldPassword );
        if( result ) {
            find(login).setPassword( newPassword );
        }
        return result;
    }
}
