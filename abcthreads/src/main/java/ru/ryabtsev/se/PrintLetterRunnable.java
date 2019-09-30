package ru.ryabtsev.se;

/**
 * Prints letter several times.
 */
public class PrintLetterRunnable implements Runnable {

    LetterPrinter printer;

    private char letter;

    private int repetitionNumber;

    /**
     * Creates new PrintLetterRunnable instance.
     * @param letter - letter to print.
     * @param repetitionNumber - number of repetitions.
     */
    public PrintLetterRunnable(LetterPrinter printer, char letter, int repetitionNumber) {
        this.printer = printer;
        this.letter = letter;
        this.repetitionNumber = repetitionNumber;
    }

    /**
     * @inheritDoc
     */
    public void run() {
        for( int i = 0; i < repetitionNumber; ++i ) {
            printer.print( letter );
        }
    }
}
