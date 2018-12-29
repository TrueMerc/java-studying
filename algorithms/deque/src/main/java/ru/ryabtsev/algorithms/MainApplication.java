package ru.ryabtsev.algorithms;

import ru.ryabtsev.algorithms.collections.PriorityQueue;
import ru.ryabtsev.algorithms.collections.Queue;
import ru.ryabtsev.algorithms.collections.Stack;

/**
 * Main application.
 */
public class MainApplication
{
    public static void main( String[] args )
    {
        processHomeworkFirstPart();
        processHomeworkSecondPart();
        processHomeworkThirdPart();
    }

    private static void processHomeworkFirstPart() {
        processStack();
        processQueue();
        processPriorityQueue();
    }

    private static void processStack() {
        System.out.println("STACK STRUCTURE TESTING...");

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

        System.out.println("...COMPLETE");
    }

    private static void processQueue() {
        System.out.println("QUEUE STRUCTURE TESTING...");

        final int depth = 4;
        Queue<Integer> queue = new Queue<>(depth);

        if(!queue.isEmpty()) {
            System.out.println("Queue construction error.");
        }

        System.out.println("Testing 'insert'....");

        for( int i = 0; !queue.isFull() && i < depth; ++i ) {
            System.out.println(queue);
            queue.insert(i);
        }

        if(!queue.isFull()) {
            System.out.println("Queue 'insert' error.");
        }
        else {
            System.out.println(queue);
        }

        System.out.println("Testing 'peek'....");
        if( queue.peek() != 0 ) {
            System.out.println("Queue 'peek' error.");
        }


        System.out.println("Testing 'remove'....");

        while( !queue.isEmpty() ) {
            System.out.println(queue);
            queue.remove();
        }

        if(!queue.isEmpty()) {
            System.out.println("Queue 'remove' error.");
        }
        else {
            System.out.println(queue);
        }

        System.out.println("...COMPLETE");
    }

    private static void processPriorityQueue() {
        System.out.println("PRIORITY QUEUE STRUCTURE TESTING...");

        final int depth = 4;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(depth);

        if(!priorityQueue.isEmpty()) {
            System.out.println("Priority queue construction error.");
        }

        System.out.println("Testing 'insert'....");

        for( int i = 0; !priorityQueue.isFull() && i < depth; ++i ) {
            int priority = (i % 2 == 0) ? 2 * (i + 1) : i;
            System.out.println(priorityQueue);
            priorityQueue.insert(priority);
        }

        if(!priorityQueue.isFull()) {
            System.out.println("Priority queue 'insert' error.");
        }
        else {
            System.out.println(priorityQueue);
        }

        System.out.println("Testing 'peek'....");
        if( priorityQueue.peek() != 6 ) {
            System.out.println("Queue 'peek' error.");
        }


        System.out.println("Testing 'remove'....");

        while( !priorityQueue.isEmpty() ) {
            System.out.println(priorityQueue);
            priorityQueue.remove();
        }

        if(!priorityQueue.isEmpty()) {
            System.out.println("Priority queue 'remove' error.");
        }
        else {
            System.out.println(priorityQueue);
        }

        System.out.println("...COMPLETE");
    }


    private static void processHomeworkSecondPart() {

    }

    private static void processHomeworkThirdPart() {

    }
}


