package ru.ryabtsev.se.server.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.User;
import ru.ryabtsev.se.server.jdbc.JdbcConnectionManager;
import ru.ryabtsev.se.server.jdbc.dto.UserRegistrationDTO;

import javax.enterprise.context.ApplicationScoped;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//@Dependent
@ApplicationScoped
public class JdbcUserServiceBean implements UserService {

    private static final String DEFAULT_SELECT_QUERY = "SELECT * FROM users WHERE login = ?";
    private final JdbcConnectionManager connectionManager = new JdbcConnectionManager();

    @Override
    @SneakyThrows
    public @Nullable User find(@Nullable String login) {
        //final String loginQuery = "SELECT * FROM '" + connectionManager.getConfiguration().getDatabaseName() + "' WHERE 'login' = ?";
        final String query = DEFAULT_SELECT_QUERY;
        connectionManager.connect();
        PreparedStatement statement = connectionManager.createPreparedStatement(query);
        statement.setString( 1, login );
        ResultSet result = statement.executeQuery();
        User user = result.next() ? new User( result.getString("login"), result.getString("password"), result.getString("nickname") ) : null;
        connectionManager.disconnect();
        return user;
    }

    @Override
    @SneakyThrows
    public boolean check(@Nullable String login, @Nullable String password) {
        final String query = DEFAULT_SELECT_QUERY;
        connectionManager.connect();
        PreparedStatement statement = connectionManager.createPreparedStatement(query);
        statement.setString( 1, login );
        ResultSet resultSet = statement.executeQuery();
        boolean result = resultSet.next() ? password.equals( resultSet.getString("password") ) : false;
        connectionManager.disconnect();
        return result;
    }

    @Override
    @SneakyThrows
    public boolean registry(@Nullable String login, @Nullable String password) {
        if( exists(login) ) {
            return false;
        }
        else {
            final UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO( login, password, "Default");
            connectionManager.connect();
            final String loginQuery = "INSERT INTO users (`id`, `login`, `password`, `nickname`) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
            statement.setString( 1, userRegistrationDTO.getId() );
            statement.setString( 2, userRegistrationDTO.getLogin() );
            statement.setString( 3, userRegistrationDTO.getPassword() );
            statement.setString( 4, userRegistrationDTO.getNickname() );
            boolean result = statement.executeUpdate() > 0;
            connectionManager.disconnect();
            return result;
        }
    }

    @Override
    @SneakyThrows
    public boolean exists(@Nullable String login) {
        //final String query = "SELECT * FROM " + connectionManager.getConfiguration().getDatabaseName() + " WHERE login = ?";
        //final String query = "SELECT * FROM users WHERE login = ?";
        final String query = DEFAULT_SELECT_QUERY;
        System.out.println("Exists query: " + query);
        connectionManager.connect();
        PreparedStatement statement = connectionManager.createPreparedStatement(query);
        statement.setString( 1, login );
        ResultSet result = statement.executeQuery();
        boolean exists = result.next();
        connectionManager.disconnect();
        return exists;
    }

    @Override
    @SneakyThrows
    public boolean setNickname(@Nullable String login, @Nullable String nick) {
        final String query = "UPDATE users SET nickname = ? WHERE login = ?";
        connectionManager.connect();
        PreparedStatement statement = connectionManager.createPreparedStatement(query);
        statement.setString( 1, nick );
        statement.setString( 2, login );
        boolean result = statement.executeUpdate() > 0;
        connectionManager.disconnect();
        return result;
    }

    @Override
    @SneakyThrows
    public boolean setPassword(@Nullable String login, @Nullable String oldPassword, @Nullable String newPassword) {
        if( check( login, oldPassword ) ) {
            final String query = "UPDATE users SET password = ? WHERE login = ?";
            connectionManager.connect();
            PreparedStatement statement = connectionManager.createPreparedStatement(query);
            statement.setString( 1, newPassword );
            statement.setString( 2, login );
            boolean result = statement.executeUpdate() > 0;
            connectionManager.disconnect();
            return result;
        }
        return false;
    }
}
