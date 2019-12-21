package ru.ryabtsev.collection;

public class LightListApplication {
    private static final int LIST_SIZE = 5;

    public static void main(String[] args) {
        processLightLinkedList();
    }

    private static void processLightLinkedList() {
        LightList<Integer> integerList = new LinkedLightList<>();

        if(integerList.isEmpty() == false || integerList.size() != 0) {
            System.out.println("Size error.");
        }

        for(int i = 0; i < LIST_SIZE; ++i) {
            integerList.add(i, i + 1);
        }

        if(LIST_SIZE != integerList.size()) {
            System.out.println("Elements addition error.");
        }

        printList(integerList);

        for(int i = 0; i < LIST_SIZE; ++i) {
            integerList.set(i, LIST_SIZE - i);
        }

        printList(integerList);

        if(!(integerList.remove(LIST_SIZE))) {
            System.out.println("Element removing error.");
        }

        printList(integerList);

        if(integerList.indexOf(integerList.size()) != 0) {
            System.out.println("IndexOf method error.");
        }
    }

    private static <T> void printList(LightList<T> list) {
        for(int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }
}
