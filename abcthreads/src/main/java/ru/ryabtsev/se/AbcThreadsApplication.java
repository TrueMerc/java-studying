package ru.ryabtsev.se;

/**
 * Three threads print letters 'A', 'B', 'C' (one letter for each).
 * Purpose: print sequence 'ABCABCABCABCABCABC' using wait()/notify()/notifyAll() methods.
 *
 */
public class AbcThreadsApplication
{
    private static final char PRINTED_LETTERS[] = { 'A', 'B', 'C' };
    private static int REPETITION_NUMBER = 5;

    public static void main( String[] args ) throws InterruptedException {
        LetterPrinter printer = new LetterPrinter( PRINTED_LETTERS );

        Thread threads[] = new Thread[PRINTED_LETTERS.length];

        // Specially starts print threads in inverted order for test purpose only.
        for( int i = PRINTED_LETTERS.length - 1; i > -1 ; --i ) {
            threads[i] = new Thread( new PrintLetterRunnable(printer, PRINTED_LETTERS[i], REPETITION_NUMBER) );
            threads[i].start();
        }

        for( Thread thread : threads ) {
            thread.join();
        }
    }
}
