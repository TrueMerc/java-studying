package ru.ryabtsev.se;

/**
 * Interface for function objects.
 */
public interface Function {
    /**
     *
     * @param argument - function argument.
     * @return Returns function value.
     */
    public float value( float argument );

    /**
     *
     * @param initialValue - function argument.
     * @param elementNumber - number of element in array.
     * @return Returns function value.
     */
    public float value( float initialValue, long elementNumber );

}
