package ru.ryabtsev.collection;

import java.util.ArrayList;

public class LightListApplication {
    private static final int LIST_SIZE = 5;

    public static void main(String[] args) {
        processLightLinkedList();
        processArrayLightList();
    }

    private static void processLightLinkedList() {
        System.out.println("PROCESSING LINKED LIST");
        LightList<Integer> list = new LinkedLightList<>();
        processList(list);
    }

    private static void processArrayLightList() {
        System.out.println("PROCESSING ARRAY LIST");
        LightList<Integer> list = new ArrayLightList<>();
        processList(list);
//        if(list.isEmpty() == false || list.size() != 0) {
//            System.out.println("Size error.");
//        }
//
//        for(int i = 0; i < LIST_SIZE; ++i) {
//            list.add(i, i + 1);
//        }
//
//        if(LIST_SIZE != list.size()) {
//            System.out.println("Elements addition error.");
//        }
//
//        printList(list);
    }

    private static void processList(LightList<Integer> integers) {
        LightList<Integer> list = integers;

        if(!list.isEmpty() || list.size() != 0) {
            System.out.println("Size error.");
        }

        for(int i = 0; i < LIST_SIZE; ++i) {
            list.add(i, i + 1);
        }

        if(LIST_SIZE != list.size()) {
            System.out.println("Elements addition error.");
        }

        printList(list);

        for(int i = 0; i < LIST_SIZE; ++i) {
            list.set(i, LIST_SIZE - i);
        }

        printList(list);

        if(!(list.remove(LIST_SIZE))) {
            System.out.println("Element removing error.");
        }

        printList(list);

        if(list.indexOf(list.size()) != 0) {
            System.out.println("IndexOf method error.");
        }
    }

    private static <T> void printList(LightList<T> list) {
        for(int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }
}
