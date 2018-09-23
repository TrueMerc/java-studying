package ru.ryabtsev.se;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ThirdHomeworkWordsApplicationTest
{

    @Test
    public void testEmptyWordsArray()
    {
        String[] noWords = {};
        WordsMap wordsMap = new WordsMap( noWords );

        wordsMap.printUniqueWords();
        wordsMap.printWordsWithOccurrencesNumber();
    }

    @Test
    public void testEveryWordIsUnique() {
        String[] uniqueWords = { "Cat", "Dog", "Wolf" };
        WordsMap wordsMap = new WordsMap( uniqueWords );

        wordsMap.printUniqueWords();
        wordsMap.printWordsWithOccurrencesNumber();

    }

    @Test
    public void testWordRepetition() {
        String[] uniqueWords = { "Cat", "Cat", "Cat" };
        WordsMap wordsMap = new WordsMap( uniqueWords );

        wordsMap.printUniqueWords();
        wordsMap.printWordsWithOccurrencesNumber();

    }

}
