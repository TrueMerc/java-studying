package ru.ryabtsev.se.server.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.User;
import ru.ryabtsev.se.server.jdbc.JdbcConnectionManager;
import ru.ryabtsev.se.server.jdbc.dto.UserRegistrationDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class JdbcUserServiceBean implements UserService {


    //private static final String FIND_BY_LOGIN_QUERY = "SELECT * FROM 'users_registration'  WHERE login '' = ?";

    private final JdbcConnectionManager connectionManager = new JdbcConnectionManager();
    private final Map<String, User> registeredUsers = new LinkedHashMap<>();

    @Override
    public @Nullable User find(@Nullable String login) {
        return null;
    }

    @Override
    public boolean check(@Nullable String login, @Nullable String password) {
        return false;
    }

    @Override
    @SneakyThrows
    public boolean registry(@Nullable String login, @Nullable String password) {
        if( exists(login) ) {
            return false;
        }
        else {
            final UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO( login, password, "");
            final String loginQuery = "INSERT INTO '" +
                                       connectionManager.getConfiguration().getDatabaseName() +
                                       "' ('id', 'login', 'password', 'nickname') VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
            statement.setString( 1, userRegistrationDTO.getId() );
            statement.setString( 2, userRegistrationDTO.getLogin() );
            statement.setString( 3, userRegistrationDTO.getPassword() );
            statement.setString( 4, userRegistrationDTO.getNickname() );
            ResultSet result = statement.executeQuery();
            return result.getBoolean("login");
        }
    }

    @Override
    @SneakyThrows
    public boolean exists(@Nullable String login) {
        final String loginQuery = "SELECT * FROM '" + connectionManager.getConfiguration().getDatabaseName() + "' WHERE 'login' = ?";
        PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
        statement.setString( 1, login );
        ResultSet result = statement.executeQuery();
        return result.getBoolean("login");
    }

    @Override
    public boolean setNickname(@Nullable String login, @Nullable String nick) {
        return false;
    }

    @Override
    public boolean setPassword(@Nullable String login, @Nullable String oldPassword, @Nullable String newPassword) {
        return false;
    }
}
