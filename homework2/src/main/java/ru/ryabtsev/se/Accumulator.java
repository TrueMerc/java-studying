package ru.ryabtsev.se;
import ru.ryabtsev.se.exception.*;

public class Accumulator {
    final static public int DEFAULT_ROWS_NUMBER = 4;
    final static public int DEFAULT_COLUMNS_NUMBER = 4;

    private int rowsNumber;
    private int columnsNumber;

    Accumulator() {
        rowsNumber = DEFAULT_ROWS_NUMBER;
        columnsNumber = DEFAULT_COLUMNS_NUMBER;
    }

    Accumulator( int rowsNumber, int columsNumber ) {
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columsNumber;
    }

    public int accumulate( String[][] array ) throws MyArraySizeException, MyArrayDataException {
        checkArraySize( array );
        int result = 0;
        for( int row = 0; row < rowsNumber; ++row) {
            for( int column = 0; column < columnsNumber; ++column ) {
                try {
                    result += Integer.parseInt( array[row][column] );
                }
                catch(NumberFormatException nfe) {
                    throw new MyArrayDataException( nfe.getMessage(), row, column );
                }
            }
        }
        return result;
    }

    private void checkArraySize( String[][] array ) throws MyArraySizeException {
        checkRowsNumber( array );
        checkColumnsNumber( array );
    }

    private void checkColumnsNumber( String[][] array ) throws MyArraySizeException {
        for( String[] row : array ) {
            if( row.length != columnsNumber ) {
                throw new MyArraySizeException( "Wrong input array columns number." );
            }
        }
    }

    private void checkRowsNumber( String[][] array ) throws  MyArraySizeException {
        if( rowsNumber != array.length ) {
            throw new MyArraySizeException( "Wrong input array rows number." );
        }
    }
}
