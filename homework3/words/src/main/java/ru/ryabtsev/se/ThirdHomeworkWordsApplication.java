package ru.ryabtsev.se;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main WordsSet application class.
 */
public class ThirdHomeworkWordsApplication
{
    final static String[] WORDS = {  "Dog", "Cat", "Cow", "Wolf",
                                     "Chicken", "Ape", "Monkey", "Sheep",
                                     "Parrot", "Moose", "Duck", "Goose",
                                     "Parrot", "Cat", "Cow", "Wolf",
                                     "Ape", "Duck", "Dog", "Wolf" };

    public static void main( String[] args )
    {
        WordsMap wordsMap = new WordsMap( WORDS );

        wordsMap.printUniqueWords();

        wordsMap.printWordsWithOccurrencesNumber();
    }
}
