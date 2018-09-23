package ru.ryabtsev.se;


import ru.ryabtsev.se.exception.MyArrayDataException;
import ru.ryabtsev.se.exception.MyArraySizeException;

/**
 * Основной класс приложения.
 */
public class SecondHomeworkApplication {
    public static void main(String[] args) {
        String[][] array = { { "1", "2", "3", "4" },
                             { "5", "6", "7", "8" },
                             { "9", "10", "11", "12" },
                             { "13", "14", "15", "16" } };

        Accumulator accumulator = new Accumulator();

        try {
            System.out.println( "Calculation result: " + accumulator.accumulate(array) );
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            int row = e.getRow();
            int column = e.getColumn();
            System.out.println( "Wrong data in cell [" + row +", " + column + "] = " + array[row][column] );
        }
    }
}
