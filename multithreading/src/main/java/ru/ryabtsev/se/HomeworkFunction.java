package ru.ryabtsev.se;

/**
 * Class calculates homework function values.
 */
public class HomeworkFunction implements Function {
    @Override
    public float value( float argument, long elementNumber ) {
        float firstArg =  (float)(0.2 * argument + elementNumber / 5);
        float secondArg = (float)(0.4 * argument + elementNumber / 2);
        return (float)( argument * Math.sin( firstArg ) * Math.cos( firstArg ) * Math.cos( secondArg) );
    }
}
