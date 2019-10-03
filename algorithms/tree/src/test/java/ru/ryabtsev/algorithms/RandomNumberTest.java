package ru.ryabtsev.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class RandomNumberTest {

    @Test
    public void valuesRangeTest() {
        Random random = new Random(-100, 100);

        for(int i = 0; i < 1000000; ++i) {
            int value = random.getValue();
            Assert.assertTrue( (random.getLeftBorder() <= value) && (value <= random.getRightBorder()) );
        }
    }
}
