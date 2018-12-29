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

        System.out.println("Testing 'push'....");

        for( int i = 0; !stack.isFull() && i < depth; ++i ) {
            System.out.println(stack);
            stack.push(i);
        }

        if(!stack.isFull()) {
            System.out.println("Stack 'push' error.");
        }
        else {
            System.out.println(stack);
        }

        System.out.println("Testing 'pop'....");

        while( !stack.isEmpty() ) {
            System.out.println(stack);
            stack.pop();
        }

        if(!stack.isEmpty()) {
            System.out.println("Stack 'pop' error.");
        }
        else {
            System.out.println(stack);
        }
    }
}
