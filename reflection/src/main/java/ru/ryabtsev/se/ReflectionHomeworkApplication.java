package ru.ryabtsev.se;

import ru.ryabtsev.se.exception.TestSuiteException;

/**
 * 'Reflection' homework application main class.
 */
public class ReflectionHomeworkApplication
{
    /**
     * Main method.
     * @param args - command line arguments.
     */
    public static void main( String[] args )
    {
        try {
            final TestSuiteExecutionResult result = TestExecutor.start( "ru.ryabtsev.se.TestSuite" );

            System.out.println("Test suite execution result: "
                    + result.getSucceededNumber() + " tests succeeded, "
                    + result.getFailedNumber() + " tests failed.");
        }
        catch( TestSuiteException e ) {
            System.out.println( e.getMessage() );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
