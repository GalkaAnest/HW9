package hw9.ex1;

import java.util.ArrayList;

public class ForTesting {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        // Adding elements
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // Getting size
        System.out.println("Size: " + list.size());

        // Getting elements
        System.out.println("Element at index 0: " + list.get(0));
        System.out.println("Element at index 1: " + list.get(1));
        System.out.println("Element at index 2: " + list.get(2));

        // Removing an element
        list.remove(1);

        // Clearing the collection
        list.clear();

        // Getting size after removing and clearing
        System.out.println("Size: " + list.size());
    }

}
