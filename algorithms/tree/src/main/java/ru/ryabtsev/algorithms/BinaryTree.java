package ru.ryabtsev.algorithms;

public class BinaryTree {

    private class Node {
        Node leftChild;
        Node rightChild;
        int value;

        Node(int value) {
            this.leftChild = null;
            this.rightChild = null;
            this.value = value;
        }
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(int value) {
        root = recursiveAdd(root, value);
    }

    private Node recursiveAdd(Node currentNode, int value) {
        if(currentNode == null) {
            currentNode = new Node(value);
        }

        if(value < currentNode.value) {
            currentNode.leftChild = recursiveAdd(currentNode.leftChild, value);
        }
        else if(value > currentNode.value) {
            currentNode.rightChild = recursiveAdd(currentNode.rightChild, value);
        }

        return currentNode;
    }

    public boolean find(int value) {
        return recursiveFind(root, value);
    }

    public boolean recursiveFind(Node currentNode, int value) {
        if(currentNode == null) {
            return false;
        }

        if(currentNode.value == value) {
            return true;
        }

        return value < currentNode.value ?
                recursiveFind(currentNode.leftChild, value) : recursiveFind(currentNode.rightChild, value);
    }

    public int depth() {
        return recursiveDepth(root);
    }

    private int recursiveDepth(Node currentNode) {
        if(currentNode == null) {
            return 0;
        }

        return 1 + Math.max(recursiveDepth(currentNode.leftChild), recursiveDepth(currentNode.rightChild));
    }


    public boolean isBalanced() {
        return recursiveIsBalanced(root);
    }

    public boolean recursiveIsBalanced(Node currentNode) {
        if(currentNode == null) {
            return true;
        }

        if( Math.abs(recursiveDepth(currentNode.leftChild) - recursiveDepth(currentNode.rightChild)) > 1) {
            return false;
        }

        return recursiveIsBalanced(currentNode.leftChild) && recursiveIsBalanced(currentNode.rightChild);
    }

}
