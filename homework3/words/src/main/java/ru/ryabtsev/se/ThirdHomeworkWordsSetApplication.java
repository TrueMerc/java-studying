package ru.ryabtsev.se;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main WordsSet application class.
 */
public class ThirdHomeworkWordsSetApplication
{
    final static String[] WORDS = {  "Dog", "Cat", "Cow", "Wolf",
                                     "Chicken", "Ape", "Monkey", "Sheep",
                                     "Parrot", "Moose", "Duck", "Goose",
                                     "Parrot", "Cat", "Cow", "Wolf",
                                     "Ape", "Duck", "Dog", "Wolf" };

    public static void main( String[] args )
    {
        ArrayList<String> wordsList = new ArrayList<>( Arrays.asList( WORDS ) );
        System.out.println( "Initial words list:" );
        System.out.println( wordsList );

        HashMap<String, Integer> wordsMap = new HashMap<>();

        for( String word : wordsList ) {
            if( wordsMap.get( word ) == null ) {
                wordsMap.put( word, 1 );
            }
            else {
                wordsMap.put( word, wordsMap.get( word ) + 1);
            }
        }

        System.out.println( "Unique words:");
        System.out.println( wordsMap.keySet() );

        System.out.println( "Words with occurrences number:");
        System.out.println( wordsMap );
    }
}
