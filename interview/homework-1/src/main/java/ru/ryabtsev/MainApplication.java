package ru.ryabtsev;

import ru.ryabtsev.builder.Person;
import ru.ryabtsev.cars.Car;
import ru.ryabtsev.cars.LightWeightCar;
import ru.ryabtsev.cars.Lorry;
import ru.ryabtsev.polymorphism.Circle;
import ru.ryabtsev.polymorphism.Shape;
import ru.ryabtsev.polymorphism.Square;
import ru.ryabtsev.polymorphism.Triangle;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Implements the entry point of the first homework of 'Interview preparation' course.
 */
public class MainApplication
{
    public static void main( String[] args ) {
        firstPart();
        secondPart();
        thirdPart();
    }

    /**
     * Implements the first part of the first homework of 'Interview preparation' course.
     */
    public static void firstPart() {
        System.out.println("---------------- First Part ----------------");
        final Person person = Person.getBuilder()
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

    /**
     * Implements the second part of the first homework of 'Interview preparation' course.
     */
    public static void secondPart() {
        System.out.println("---------------- Second Part ----------------");
        final Car lorry = new Lorry();
        final Car lightWeightCar = new LightWeightCar();

        lorry.start();
        lorry.move();
        lorry.stop();
        lorry.open();

        lightWeightCar.start();
        lightWeightCar.move();
        lightWeightCar.stop();
        lightWeightCar.open();
    }

    /**
     * Implements the third part of the first homework of 'Interview preparation' course.
     */
    public static void thirdPart() {
        System.out.println("---------------- Third Part ----------------");

        Collection<Shape> shapes = new LinkedList<>();
        shapes.add(new Circle(1.));
        shapes.add(new Square(2.));
        shapes.add(new Triangle(3., 4., 5.));

        for(Shape shape : shapes) {
            System.out.println(shape.name() + ": area = " + shape.area() + ", perimeter = " + shape.perimeter());
        }
    }
}
