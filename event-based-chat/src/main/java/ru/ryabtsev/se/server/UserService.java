package ru.ryabtsev.se.server;

import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.packets.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manages chat users.
 */
@ApplicationScoped
public class UserService {
    private Map<String, User> users = new LinkedHashMap<>();

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

    public boolean check(@NotNull final String login, @NotNull final String password) {
        return  exists(login) && users.get(login).getPassword().equals( password ) ;
    }

    public boolean exists(@NotNull final String login) {
        return users.containsKey(login);
    }
}
