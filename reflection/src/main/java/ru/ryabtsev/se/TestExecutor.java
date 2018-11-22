package ru.ryabtsev.se;

import ru.ryabtsev.se.annotation.AfterSuite;
import ru.ryabtsev.se.annotation.BeforeSuite;
import ru.ryabtsev.se.annotation.Priority;
import ru.ryabtsev.se.annotation.Test;
import ru.ryabtsev.se.exception.AnnotationErrorException;
import ru.ryabtsev.se.exception.FinalizationException;
import ru.ryabtsev.se.exception.TestSuiteException;
import ru.ryabtsev.se.exception.InitializationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Executes test suites with conventional API.
 * Test suite class should contains:
 * <ol>
 * <li>single method annotated as {@code @BeforeSuite} that initializes test suite (allocates resources, opens files, etc.);</li>
 * <li>the set of methods where every method should be annotated as {@code @Test} and can be annotated with {@code @Priority},
 * which allows to set method execution order;
 * </>
 * <li>single method annotated as {@code @AfterSuite} that finalizes test suite (de-allocates resources, closes files, etc.).</li>
 * </ol>
 */
public class TestExecutor {

    /**
     * General types of methods in test suite.
     */
    private enum TestSuiteMethodType {

        BEFORE_SUITE_METHOD(0),
        AFTER_SUITE_METHOD(1),
        TEST_METHOD(2),
        DEFAULT_METHOD(3);

        private int value;

        /**
         * Constructor.
         * @param value - integer value which corresponding method type.
         */
        TestSuiteMethodType(final int value) {
            this.value = value;
        }

        /**
         * @return Integer value which corresponding method type.
         */
        int getValue() {
            return value;
        }
    }

    /**
     * Executes test suite.
     * @param testSuiteName test suite class name.
     * @return Test suite execution result.
     * @throws ClassNotFoundException if class with specified name doesn't exist.
     */
    public static TestSuiteExecutionResult start( String testSuiteName ) throws ClassNotFoundException {
        Class testSuite = Class.forName( testSuiteName );
        return start( testSuite );
    }

    /**
     * Executes test suite.
     * @param testSuite test suite class.
     * @return Test suite execution result.
     */
    public static TestSuiteExecutionResult start( Class testSuite ) throws TestSuiteException {

        final List<Method> executionList = new ArrayList<>();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        Method[] methods = testSuite.getMethods();

        for( Method method : methods ) {
            switch( getMethodType( method ) ) {
                case BEFORE_SUITE_METHOD:
                    if( beforeSuiteMethod == null) {
                        beforeSuiteMethod = method;
                    }
                    else {
                        throw new TestSuiteException( "There should be only one @BeforeSuite annotated method." );
                    }
                    break;
                case AFTER_SUITE_METHOD:
                    if( afterSuiteMethod == null) {
                        afterSuiteMethod = method;
                    }
                    else {
                        throw new TestSuiteException( "There should be only one @AfterSuite annotated method." );
                    }
                    break;
                case TEST_METHOD:
                {
                    executionList.add( method );
                }
                break;
                default:
                    break;
            }
        }

        initialize( beforeSuiteMethod, testSuite );
        sortByPriority( executionList );
        int succeeded = 0;
        for( Method testMethod : executionList ) {
           succeeded += executeTest( testMethod, testSuite ) ? 1 : 0;
        }
        finalize( afterSuiteMethod, testSuite );

        return new TestSuiteExecutionResult( succeeded, executionList.size() - succeeded );
    }

    /**
     * Returns test suite method type.
     */
    private static TestSuiteMethodType getMethodType( Method method ) throws AnnotationErrorException {

        boolean annotationChecks[] = { false, false, false };

        if( method.getAnnotation( BeforeSuite.class) != null ) {
            annotationChecks[0] = true;
        }

        if( method.getAnnotation( AfterSuite.class) != null ) {
            annotationChecks[1] = true;
        }

        if( method.getAnnotation( Test.class ) != null ) {
            annotationChecks[2] = true;
        }

        int index = 3;
        for( int i = 0, counter = 0; i < annotationChecks.length; ++i ) {
            counter += annotationChecks[i] ? 1 : 0;
            index = annotationChecks[i] ? i : index;
            if( counter > 1) {
                throw new AnnotationErrorException( "Different annotation applied to one method." );
            }
        }

        return TestSuiteMethodType.values()[index];
    }

    /**
     * Initializes test suite.
     * @param method initialization method.
     * @param testSuite test suite class.
     * @throws InitializationException
     */
    private static void initialize(final Method method, final Class testSuite) throws InitializationException {
        try {
            if (method == null) {
                throw new InitializationException();
            }
            method.invoke( testSuite.getConstructor().newInstance() );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new InitializationException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new InitializationException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new InitializationException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new InitializationException();
        }
    }

    /**
     * Executes test suite method.
     * @param testMethod - method which annotated as {@code @Test}
     * @param testSuite - test suite class.
     * @return Test result.
     */
    private static boolean executeTest(final Method testMethod, final Class testSuite) {
        try {
            testMethod.invoke(  testSuite.getConstructor().newInstance() );
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Finalizes test suite.
     * @param method - finalization method.
     * @param testSuite - test suite class.
     * @throws FinalizationException
     */
    private static void finalize(final Method method, final Class testSuite) throws FinalizationException {
        try {
            if (method == null) {
                throw new FinalizationException();
            }
            method.invoke( testSuite.getConstructor().newInstance() );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new FinalizationException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new FinalizationException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new InitializationException();
        }
    }

    /**
     * Descending order priority comparator.
     */
    private static class PriorityDescendingComparator implements Comparator<Method> {
        @Override
        public int compare(Method first, Method second) {
            int firstPriority = (first.getAnnotation(Priority.class) != null) ? first.getAnnotation(Priority.class).value() : 1;
            int secondPriority = (second.getAnnotation(Priority.class) != null) ? second.getAnnotation(Priority.class).value() : 1;
            return Integer.compare(secondPriority, firstPriority);
        }
    }

    /**
     * Sorts methods which annotated as {@code @Test} by priority.
     */
    private static void sortByPriority( List<Method> methods ) {
        Collections.sort( methods, new PriorityDescendingComparator() );
    }
}
