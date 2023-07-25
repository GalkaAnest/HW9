package hw9.ex4;

public class MyStack<E> {
    private Node top;
    private int size;

    public MyStack() {
        top = null;
        size = 0;
    }

    public void push(E value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }

        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            top = top.next;
        } else {
            Node currentNode = getNode(index);
            Node prevNode = getNode(index - 1);
            prevNode.next = currentNode.next;
        }

        size--;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return top.value;
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        E value = top.value;
        top = top.next;
        size--;

        return value;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(int index) {
        Node currentNode = top;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
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
        MyStack<String> stack = new MyStack<>();

        // Pushing elements
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Orange");

        // Getting size
        System.out.println("Size: " + stack.size());

        // Peeking the top element
        System.out.println("Peek: " + stack.peek());

        // Popping and removing elements
        while (!stack.isEmpty()) {
            System.out.println("Pop: " + stack.pop());
        }

        // Clearing the collection
        stack.clear();

        // Getting size after clearing
        System.out.println("Size: " + stack.size());
    }
}

