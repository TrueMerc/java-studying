package ru.ryabtsev.se;

/**
 * Class calculates homework function values.
 */
public class HomeworkFunction implements Function {
    @Override
    public float value(float argument) {
        return argument * argument;
    }

    @Override
    public float value( float argument, long elementNumber ) {
        return (float)( argument * Math.sin( 0.2 * argument + elementNumber / 5)
                                 * Math.cos( 0.2 * argument + elementNumber / 5)
                                 * Math.cos( 0.4 * argument + elementNumber / 2) );
    }
}
