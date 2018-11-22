package ru.ryabtsev.se;

import ru.ryabtsev.se.TestSuite;

/**
 * 'Reflection' homework application main class.
 */
public class ReflectionHomeworkApplication
{
    public static void main( String[] args )
    {
        TestSuite testSuite = new TestSuite();
        TestExecutor.start( testSuite.getClass() );
    }
}
