package ru.ryabtsev.se;

import java.util.Arrays;

/**
 * Class that processes arrays removing all elements before last '4' digit.
 */
public class ArrayProcessor {

    private static final int TERMINATION_NUMBER = 4;

    /**
     * Removes all elements before last element with value '4' (including this element).
     * If there is not element with value '4' - throws Runtime exception.
     * @param array - initial array.
     * @return New array with remaining elements of previous array.
     * @throws RuntimeException
     */
    static int[] removeAllBeforeLastTerminationNumber(int[] array ) throws RuntimeException {

        int index = -1;
        for( int i = 0; i < array.length; ++i) {
            if( array[i] == TERMINATION_NUMBER) {
                index = i;
            }
        }

        if( index < 0 ) {
            throw  new RuntimeException();
        }


        return Arrays.copyOfRange( array, index + 1, array.length );
    }

    /**
     * Checks that array contains units and fours simultaneously.
     * If array contains any other numbers - throws RuntimeException.
     * @param array - initial array.
     * @return true - if array contains units and fours simultaneously, false - if it is not.
     * @throws RuntimeException
     */
    static boolean checkThatArrayContainsUnitsAndFours( int[] array ) throws  RuntimeException {
        int unitsNumber = 0, foursNumber = 0;
        for( int i = 0; i < array.length; ++i ) {
            switch ( array[i]) {
                case 1:
                    ++unitsNumber;
                    break;
                case 4:
                    ++foursNumber;
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        return unitsNumber * foursNumber != 0; // Condition checks that array contains units and fours simultaneously.
    }
}
