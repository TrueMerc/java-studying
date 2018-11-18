package ru.ryabtsev.se;

import java.util.Arrays;

/**
 * Class that processes arrays removing all elements before last '4' digit.
 */
public class ArrayProcessor {

    private static final int TERMINATION_NUMBER = 4;

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
}
