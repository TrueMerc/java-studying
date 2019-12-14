package ru.ryabtsev.se.cdi;

import org.junit.Assert;
import org.junit.Test;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

public class CDITest {

    @Inject
    Singletone singletone;

    @Test
    public void singletonTest() {
        SeContainerInitializer.newInstance().addPackages(CDITest.class).initialize();
        singletone  = CDI.current().select(Singletone.class).get();
        Assert.assertTrue( singletone.getField() == 1 );
    }
}
