package ru.ryabtsev.se.exception;

public class MyArrayDataException extends MyArrayException {
    private int row = -1;
    private int column = -1;

    public MyArrayDataException( String message, int row, int column) {
        super( message );
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public  int getColumn() {
        return  column;
    }
}
