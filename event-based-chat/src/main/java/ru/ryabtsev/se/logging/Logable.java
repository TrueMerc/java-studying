package ru.ryabtsev.se.logging;

import java.util.List;

/**
 * Base interface for classes with logging ability.
 */
public interface Logable {
    /**
     * Remove all data from file.
     */
    void clear();


    /**
     * Writes string to log.
     * @param string
     */
    void write( final String string );

    /**
     * Reads all from log.
     * @return All strings from log.
     */
    List<String> readAll();

    /**
     * Reads last strings from log.
     * @param stringsNumber - strings to read.
     * @return last strings from log.
     */
    List<String> readLast(int stringsNumber);
}
