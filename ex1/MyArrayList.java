package hw9.ex1;

import java.util.Arrays;

//import static com.sun.tools.javac.util.ArrayUtils.ensureCapacity;


public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 2000000;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(E value) {
        ensureCapacity(size + 1);
        elements[size++] = value;

    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length >> 1);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }


    public int size() {
        return size;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

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

//private Object[] myArray = new ArrayList[];
