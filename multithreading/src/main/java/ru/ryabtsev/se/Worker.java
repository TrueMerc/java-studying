package ru.ryabtsev.se;

/**
 * Interface declares operations with arrays.
 */
public interface Worker {
    /**
     *
     * @param array - fill all elements of array with unit value.
     */
    public void fillWithUnits(float array[]);

    /**
     *
     * @param array - fill all elements of array with value which is calculated by {@link Function} object.
     * @param function - function object calculates new values in array cells.
     */
    public void fillWithFunction(float array[], Function function);
}