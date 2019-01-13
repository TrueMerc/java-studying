package ru.ryabtsev.collection;

/**
 * Main application class.
 */
public class MainApplication
{
    private static final int DEFAULT_SIZE = 10;

    public static void main( String[] args )
    {
        processStack();
        processQueue();
    }

    private static void processStack() {
       System.out.println("Processing stack.");
       Stack<Integer> stack = new ForwardListStack<>();

       for(int i = 0; i < DEFAULT_SIZE; ++i) {
           stack.push(i);
           System.out.println(stack);
       }
       for(; !stack.isEmpty(); ) {
           stack.pop();
           System.out.println(stack);
       }
    }

    private static void processQueue() {
        System.out.println("Processing queue.");
        Queue<Integer> queue = new ForwardListQueue<>();

        for(int i = 0; i < DEFAULT_SIZE; ++i) {
            queue.add(i);
            System.out.println(queue);
        }
        for(; !queue.isEmpty(); ) {
            queue.poll();
            System.out.println(queue);
        }
    }
}