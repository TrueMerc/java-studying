package ru.ryabtsev.algorithms;

import ru.ryabtsev.algorithms.collections.Stack;

/**
 * Main application.
 */
public class MainApplication
{
    public static void main( String[] args )
    {
        System.out.println("Stack structure example.");
        processStack();
    }

    private static void processStack() {
        final int depth = 4;
        Stack<Integer> stack = new Stack<>(depth);

        if(!stack.isEmpty()) {
            System.out.println("Stack construction error.");
        }

        for( int i = 0; i < depth; ++i ) {
           stack.push(i);
        }
        if(!stack.isFull()) {
            System.out.println("Stack 'push' error.");
        }

        System.out.println(stack);

        for( int i = 0; i < depth; ++i ) {
            stack.pop();
        }

        if(!stack.isEmpty()) {
            System.out.println("Stack 'pop' error.");
        }
    }
}
