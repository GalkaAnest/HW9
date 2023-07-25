package hw9.ex3;

public class MyQueue<E> {
    private Node head;
    private Node tail;
    private int size;

    public MyQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(E value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return head.value;
    }

    public E poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        E value = head.value;
        head = head.next;
        size--;

        if (isEmpty()) {
            tail = null;
        }

        return value;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        E value;
        Node next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        // Adding elements
        queue.add("Apple");
        queue.add("Banana");
        queue.add("Orange");

        // Getting size
        System.out.println("Size: " + queue.size());

        // Peeking the first element
        System.out.println("Peek: " + queue.peek());

        // Polling and removing elements
        while (!queue.isEmpty()) {
            System.out.println("Poll: " + queue.poll());
        }

        // Clearing the collection
        queue.clear();

        // Getting size after clearing
        System.out.println("Size: " + queue.size());
    }
}

