package ru.ryabtsev.se.jdbc;

import org.junit.Test;
import ru.ryabtsev.se.server.jdbc.Configuration;
import ru.ryabtsev.se.server.jdbc.JdbcDefaultConfiguration;

public class JdbcConnectionTest {

    @Test
    public void testToStringMethod() {
        final Configuration configuration = new JdbcDefaultConfiguration();

        System.out.println( configuration.toString() );
    }
}
