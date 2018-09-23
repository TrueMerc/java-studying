package ru.ryabtsev.se;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import ru.ryabtsev.se.exception.PhoneBookException;

public class PhoneBook {

    private int MINIMAL_INITIALIZATION_ROW_LENGTH = 2;
    private LinkedHashMap<String, ArrayList<String>> phonesMap;

    public PhoneBook() {
        phonesMap = new LinkedHashMap<>();
    }

    /**
     * @param entriesArray - two dimensional array with rows in "surname, phone number[, other phone numbers]" format.
     * @throws PhoneBookException
     */
    public PhoneBook( String[][] entriesArray ) throws PhoneBookException {
        phonesMap = new LinkedHashMap<>();

        if( entriesArray.length == 0) {
            throw new PhoneBookException( "Can't create phone book from empty array." );
        }
        for( String[] row : entriesArray ) {
            if( row.length  < MINIMAL_INITIALIZATION_ROW_LENGTH ) {
                throw new PhoneBookException( "Bad initialization row format." );
            }

            for( int i = 1; i < row.length; ++i ) {
                this.add( row[0], row[i] );
            }
        }
    }

    public void add( String surname, String number ) {
        if( phonesMap.containsKey( surname ) ) {
            phonesMap.get( surname ).add( number );
        }
        else {
            ArrayList<String> newList = new ArrayList<>();
            newList.add( number );
            phonesMap.put( surname, newList );
        }
    }

    public String[] get( String surname ) {
        String[] result = {};
        if( phonesMap.containsKey( surname ) ) {
            ArrayList<String> phones = phonesMap.get( surname );
            result = new String[ phones.size() ];
            result = phones.toArray( result );
        }

        return result;
    }

    public boolean isEmpty() {
        return phonesMap.isEmpty();
    }

    public String[] surnames() {
        String[] result = {};
        Set<String> surnamesSet = phonesMap.keySet();
        if( !( surnamesSet.isEmpty() ) ) {
            result = new String[surnamesSet.size()];
            result = surnamesSet.toArray( result );
        }

        return result;
    }
}
