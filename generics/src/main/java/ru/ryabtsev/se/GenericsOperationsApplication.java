package ru.ryabtsev.se;

import ru.ryabtsev.se.boxes.AppleBox;
import ru.ryabtsev.se.boxes.OrangeBox;
import ru.ryabtsev.se.fruits.Apple;
import ru.ryabtsev.se.fruits.Orange;

import java.util.ArrayList;
import java.util.List;

public class GenericsOperationsApplication
{
    private static final Integer intArray[] = { 1, 2, 3, 4, 5 };
    private static final String strArray[] = { "First", "Second", "Third" };

    public static void main( String[] args )
    {
        doTaskOne();
        doTaskTwo();
        doTaskThree();
    }

    private static void doTaskOne() {
        final int firstElement = 0;
        int secondElement = intArray.length - 1;

        System.out.println( "Initial integer array:" );
        printArray( intArray );
        changeElements(intArray, firstElement, secondElement);
        System.out.println( "After change of first and last element:" );
        printArray( intArray );

        secondElement = strArray.length - 1;

        System.out.println( "Initial string array:" );
        printArray( strArray );
        changeElements(strArray, firstElement, secondElement);
        System.out.println( "After change of first and last element:" );
        printArray( strArray );
    }

    private static void doTaskTwo() {
        final List<Integer> arrayList = toArrayList( intArray );
        System.out.println( "List from integer array: " );
        System.out.println( arrayList );
    }

    private static void doTaskThree() {
        final Apple singleApple = new Apple();
        final Orange singleOrange = new Orange();

        final AppleBox appleBoxOne = new AppleBox(), appleBoxTwo = new AppleBox();
        final OrangeBox orangeBoxOne = new OrangeBox(), orangeBoxTwo = new OrangeBox();

        appleBoxOne.add( singleApple );
        //orangeBoxOne.add( singleApple ); -> Cause compile error.
        orangeBoxOne.add( singleOrange );

        System.out.println( "Apple box one: " + appleBoxOne );
        System.out.println( "Orange box one: " + orangeBoxOne );

        System.out.println( "Is boxes weight equal? Answer: " + appleBoxOne.compare( orangeBoxOne ) );

        appleBoxOne.dropTo( appleBoxTwo );
        //orangeBoxOne.dropTo( appleBoxTwo ); -> Cause compile error.
        orangeBoxOne.dropTo( orangeBoxTwo );

        System.out.println( "After drop:  " );
        System.out.println( "Apple box one: " + appleBoxOne );
        System.out.println( "Orange box one: " + orangeBoxOne );
        System.out.println( "Apple box two: " + appleBoxTwo );
        System.out.println( "Orange box two: " + orangeBoxTwo );
    }

    private static <T> void changeElements(T[] array, int first, int second) {
        final T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static <T> void printArray(T[] array) {
        for(T element: array) {
            System.out.print( element + " ");
        }
        System.out.print("\n");
    }

    private static <T> List<T> toArrayList(final T[] array) {
        final List<T> result = new ArrayList<>(0);

        for( T element : array ) {
            result.add( element );
        }

        return result;
    }
}
