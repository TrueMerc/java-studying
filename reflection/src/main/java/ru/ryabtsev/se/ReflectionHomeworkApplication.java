package ru.ryabtsev.se;

import ru.ryabtsev.se.exception.TestSuiteException;

/**
 * 'Reflection' homework application main class.
 */
public class ReflectionHomeworkApplication
{
    public static void main( String[] args )
    {
        try {
            TestSuiteExecutionResult result = TestExecutor.start(TestSuite.class);

            System.out.println("Test suite execution result: "
                                + result.getSucceededNumber() + " tests succeeded, "
                                + result.getFailedNumber() + " tests failed.");
        }
        catch( TestSuiteException e ) {
            System.out.println( e.getMessage() );
        }

        try {
            TestSuiteExecutionResult result = TestExecutor.start(Class.forName("ru.ryabtsev.se.TestSuite") );

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
