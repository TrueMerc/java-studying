package ru.ryabtsev;

import ru.ryabtsev.builder.Person;

/**
 * Implements the entry point of the first homework of 'Interview preparation' course.
 */
public class MainApplication
{
    public static void main( String[] args ) {
        firstPart();
    }

    /**
     * Implements the first part of the first homework of 'Interview preparation' course.
     */
    public static void firstPart() {
        Person person = Person.getBuilder()
                .addFirstName("Ivan")
                .addLastName("Ivanov")
                .addMiddleName("Ivanovich")
                .addCountry("Russia")
                .addAddress("Moscow, Kremlin")
                .addPhone("+7-999-999-99-99")
                .addAge(30)
                .addGender("Male")
                .build();

        System.out.println(
                "Person data:\n" +
                        "Last name: " + person.getLastName() + '\n' +
                        "First name: " + person.getFirstName() + '\n' +
                        "Middle name: " + person.getMiddleName() + '\n' +
                        "Country: " + person.getCountry() + '\n' +
                        "Address: " + person.getAddress() + '\n' +
                        "Phone: " + person.getPhone() + '\n' +
                        "Age: " + person.getAge() + '\n' +
                        "Gender:" + person.getGender()
        );
    }
}
