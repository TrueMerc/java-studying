package ru.ryabtsev.se;

import ru.ryabtsev.se.exception.PhoneBookException;

/**
 * Third homework 'Phone book application' main class.
 */
public class ThirdHomeworkPhoneBookApplication
{
    public static void main( String[] args )
    {
        String[][] entriesAsArray = { { "Ivanov", "+7-999-999-99-99", "+7-999-999-99-90" },
                                      { "Petrov", "+7-999-999-99-91" },
                                      { "Kuznetsov", "+7-999-999-99-92", "+7-999-999-99-93"},
                                      { "Johnson", "+1-484-999-99-99", "+1-484-999-99-90", "+1-484-999-99-80" },
                                      { "Peterson", "+1-484-999-99-74"},
                                      { "Smith", "+1-484-999-99-66"},
                                      { "Ivanov", "+1-484-999-99-50" } };

        PhoneBook phoneBook;

        try {
            phoneBook = new PhoneBook( entriesAsArray );
        } catch (PhoneBookException e) {
            e.printStackTrace();
            phoneBook = new PhoneBook();
        }

        phoneBook.add( "Sidorov", "+7-999-999-99-10" );
        phoneBook.add( "Smith", "+1-484-999-99-77" );

        String[] surnames = phoneBook.surnames();

        for( int surnameIndex = 0; surnameIndex < surnames.length; ++surnameIndex  ) {
            String surname = surnames[ surnameIndex ];
            System.out.print( surname + " has phone number(s): " );

            String[] phones = phoneBook.get( surname );
            for( int phoneIndex = 0; phoneIndex < phones.length; ++phoneIndex ) {
                String delimiter = (phoneIndex != phones.length - 1) ? ", " : "";
                System.out.print( phones[ phoneIndex ] + delimiter );
            }
            char rowEndsWith = (surnameIndex != surnames.length - 1) ? ';' : '.';
            System.out.print( rowEndsWith + "\n");
        }
    }
}
