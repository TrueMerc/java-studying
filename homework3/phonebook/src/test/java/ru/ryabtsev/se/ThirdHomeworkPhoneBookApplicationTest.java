package ru.ryabtsev.se;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ThirdHomeworkPhoneBookApplicationTest
{
    PhoneBook phoneBook = new PhoneBook();


    /**
     * Tests empty phone book get() method.
     */
    @Test
    public void testEmptyPhoneBook() {
        String[] noPhones = phoneBook.get( "Somebody" );
        Assert.assertTrue( noPhones.length == 0 );
    }


    /**
     * Tests one phone book entry addition.
     */
    @Test
    public void testAddOneEntry()
    {
        phoneBook.add( "Ivanov", "+7-999-999-99-99" );
        String[] ivanovPhones = phoneBook.get( "Ivanov" );
        Assert.assertTrue( ivanovPhones.length == 1 );
        Assert.assertTrue( ivanovPhones[0].equals( "+7-999-999-99-99" ) );
        String[] petrovPhones = phoneBook.get( "Petrov" );
        Assert.assertTrue( petrovPhones.length == 0 );
    }

    /**
     * Tests 'more than one phone number for surname' case.
     */
    @Test
    public void moreThanOnePhoneForSurname() {
        phoneBook.add( "Ivanov", "+7-999-999-99-99" );
        phoneBook.add( "Ivanov", "+7-999-999-99-90" );
        String[] ivanovPhones = phoneBook.get( "Ivanov" );
        Assert.assertTrue( ivanovPhones.length == 2 );
        Assert.assertTrue( ivanovPhones[0].equals( "+7-999-999-99-99" ) );
        Assert.assertTrue( ivanovPhones[1].equals( "+7-999-999-99-90" ) );
    }
}
