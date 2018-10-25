package ru.ryabtsev.se.packets;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for PacketResult class.
 */
public class PacketResultTest {
    @Test
    public void getSuccessTest() {
        PacketResult packet = new PacketResult( true );

        Assert.assertTrue( packet.isSuccess() );
    }
}
