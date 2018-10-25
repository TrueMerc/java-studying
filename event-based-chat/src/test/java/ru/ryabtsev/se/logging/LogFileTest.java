package ru.ryabtsev.se;

import org.junit.Test;
import ru.ryabtsev.se.logging.LogFile;
import ru.ryabtsev.se.logging.Logable;

/**
 * Test class for LogFile class.
 */
public class LogFileTest {

    @Test
    public void readAllTest() {
        Logable log = new LogFile("temp");
    }

}
