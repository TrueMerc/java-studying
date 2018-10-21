package ru.ryabtsev.se.server.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.User;
import ru.ryabtsev.se.server.jdbc.JdbcConnectionManager;
import ru.ryabtsev.se.server.jdbc.dto.UserRegistrationDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//@Dependent
@ApplicationScoped
public class JdbcUserServiceBean implements UserService {

    private final JdbcConnectionManager connectionManager = new JdbcConnectionManager();

    @Override
    @SneakyThrows
    public @Nullable User find(@Nullable String login) {
        final String loginQuery = "SELECT * FROM '" + connectionManager.getConfiguration().getDatabaseName() + "' WHERE 'login' = ?";
        PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
        statement.setString( 1, login );
        ResultSet result = statement.executeQuery();
        return result.first() ? new User( result.getString("login"), result.getString("password"), result.getString("nickname") ) : null;
    }

    @Override
    @SneakyThrows
    public boolean check(@Nullable String login, @Nullable String password) {
        final String loginQuery = "SELECT * FROM '" + connectionManager.getConfiguration().getDatabaseName() + "' WHERE 'login' = ?";
        PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
        statement.setString( 1, login );
        ResultSet result = statement.executeQuery();
        return password.equals( result.getString("password") );
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
            return statement.execute();
        }
    }

    @Override
    @SneakyThrows
    public boolean exists(@Nullable String login) {
        final String loginQuery = "SELECT '" + connectionManager.getConfiguration().getDatabaseName() + "' WHERE 'login' = ?";
        PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
        statement.setString( 1, login );
        return statement.execute();
    }

    @Override
    @SneakyThrows
    public boolean setNickname(@Nullable String login, @Nullable String nick) {
        final String loginQuery = "UPDATE '" + connectionManager.getConfiguration().getDatabaseName() + "' SET 'nickname' = ? WHERE 'login' = ?";
        PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
        statement.setString( 1, nick );
        statement.setString( 2, login );
        return statement.execute();
    }

    @Override
    @SneakyThrows
    public boolean setPassword(@Nullable String login, @Nullable String oldPassword, @Nullable String newPassword) {
        if( check( login, oldPassword ) ) {
            final String loginQuery = "UPDATE '" + connectionManager.getConfiguration().getDatabaseName() + "' SET 'password' = ? WHERE 'login' = ?";
            PreparedStatement statement = connectionManager.createPreparedStatement(loginQuery);
            statement.setString( 1, newPassword );
            statement.setString( 2, login );
            return statement.execute();

        }
        return false;
    }
}
