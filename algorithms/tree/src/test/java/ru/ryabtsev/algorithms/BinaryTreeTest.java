package ru.ryabtsev.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void depthTest() {
        BinaryTree tree = new BinaryTree();

        tree.add(8);
        tree.add(6);
        tree.add(4);
        Assert.assertEquals(3, tree.depth());

        tree.add(12);
        tree.add(10);
        tree.add(14);
        Assert.assertEquals(3, tree.depth());

        tree.add(16);
        Assert.assertEquals(4, tree.depth());
    }



}
