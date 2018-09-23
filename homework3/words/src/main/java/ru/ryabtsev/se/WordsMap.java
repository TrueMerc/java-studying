package ru.ryabtsev.se;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class WordsMap {
    private LinkedHashMap<String, Integer> wordsOccurrencesMap;


    public WordsMap() {
        wordsOccurrencesMap = new LinkedHashMap<>();
    }

    public  WordsMap( String[] words ) {
        wordsOccurrencesMap = new LinkedHashMap<>();

        for( String word : words ) {
            if( wordsOccurrencesMap.containsKey( word ) ) {
                wordsOccurrencesMap.put( word, wordsOccurrencesMap.get( word ) + 1);
            }
            else {
                wordsOccurrencesMap.put( word, 1 );
            }
        }
    }


    public void printUniqueWords() {
        System.out.println( "Unique words:");
        System.out.println( wordsOccurrencesMap.keySet() );
    }

    public  void printWordsWithOccurrencesNumber() {
        System.out.println( "Words with occurrences number:");
        System.out.println( wordsOccurrencesMap );
    }
}
