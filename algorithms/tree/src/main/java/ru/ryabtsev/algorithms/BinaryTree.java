package ru.ryabtsev.algorithms;

/**
 * Represents binary tree structure with integer values in its nodes.
 */
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

    /**
     * Constructs empty binary tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Return true if this tree is empty or false in other case
     * @return
     */
    public boolean isEmpty() {
        return null == root;
    }

    /**
     * Adds new node with given value if node with given value isn't exist yet.
     * @param value new node value.
     */
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

    /**
     * Returns true if this binary tree contains the given value.
     * @param value value whose presence in the list is to be tested.
     * @return true if given value has found or false in other case.
     */
    public boolean find(int value) {
        return recursiveFind(root, value);
    }

    private boolean recursiveFind(Node currentNode, int value) {
        if(currentNode == null) {
            return false;
        }

        if(currentNode.value == value) {
            return true;
        }

        return value < currentNode.value ?
                recursiveFind(currentNode.leftChild, value) : recursiveFind(currentNode.rightChild, value);
    }

    /**
     * Returns the depth of this tree.
     * @return the depth of this tree.
     */
    public int depth() {
        return recursiveDepth(root);
    }

    private int recursiveDepth(Node currentNode) {
        if(currentNode == null) {
            return 0;
        }

        return 1 + Math.max(recursiveDepth(currentNode.leftChild), recursiveDepth(currentNode.rightChild));
    }

    /**
     * Returns true if this tree is AVL-balanced
     * (i.e. difference between its left and right sub-trees is less or equal 1) or false in the other case.
     * @return true if this tree is AVL-balanced or false in the other case.
     */
    public boolean isBalanced() {
        return recursiveIsBalanced(root);
    }

    private boolean recursiveIsBalanced(Node currentNode) {
        if(currentNode == null) {
            return true;
        }

        if( Math.abs(recursiveDepth(currentNode.leftChild) - recursiveDepth(currentNode.rightChild)) > 1) {
            return false;
        }

        return recursiveIsBalanced(currentNode.leftChild) && recursiveIsBalanced(currentNode.rightChild);
    }

}
