package ru.ryabtsev.se;

/**
 * Prints letters.
 */
public class LetterPrinter {

    private final char letters[];

    private char currentLetterIndex = 0;

    /**
     * Constructs LetterPrinter class.
     * @param letters - acceptable letters.
     */
    public LetterPrinter(char[] letters) {
        this.letters = letters;
    }

    /**
     * Prints letter.
     * @param letter - letter.
     */
    public synchronized void print( char letter ) {
        while(letters[currentLetterIndex] != letter) {
            try {
                wait();
            }
            catch (InterruptedException exception) {
                System.out.println( exception.toString() );
            }
        }
        System.out.print( letter );
        currentLetterIndex = (char)( (++currentLetterIndex) % (letters.length) );
        notifyAll();
    }
}
