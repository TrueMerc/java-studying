package ru.ryabtsev.se.logging;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test class for LogFile class.
 */
public class LogFileTest {

    @Test
    public void readAllTest() {
        Logable log = new LogFile("temp");
        final String firstMessage = "First message";
        final String secondMessage = "Second message";

        log.write( firstMessage );
        log.write( secondMessage );

        List<String> messages = log.readAll();

        for( String message: messages) {
            System.out.println(message);
        }

        Assert.assertTrue( firstMessage.equals( messages.get(0) ) );
        Assert.assertTrue( secondMessage.equals( messages.get(1) ) );
        log.clear();
    }
}
