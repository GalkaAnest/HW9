package hw9.ex5;

import java.util.Objects;

public class MyHashMap2 {
    private static final int INITIAL_CAPACITY = 16;
    private Node[] buckets;
    private int size;

    public MyHashMap2() {
        this.buckets = new Node[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void put(Object key, Object value) {
        int index = getIndex(key);
        Node newNode = new Node(key, value);

        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            Node current = buckets[index];
            while (current.next != null) {
                if (Objects.equals(current.key, key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (Objects.equals(current.key, key)) {
                current.value = value;
            } else {
                current.next = newNode;
            }
        }

        size++;
        if (size >= buckets.length) {
            increaseCapacity();
        }
    }

    public void remove(Object key) {
        int index = getIndex(key);
        Node current = buckets[index];
        Node prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public void clear() {
        buckets = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object get(Object key) {
        int index = getIndex(key);
        Node current = buckets[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    private int getIndex(Object key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void increaseCapacity() {
        Node[] newBuckets = new Node[buckets.length * 2];
        for (Node node : buckets) {
            while (node != null) {
                int newIndex = Math.abs(node.key.hashCode()) % newBuckets.length;
                Node newNode = new Node(node.key, node.value);
                newNode.next = newBuckets[newIndex];
                newBuckets[newIndex] = newNode;
                node = node.next;
            }
        }
        buckets = newBuckets;
    }

    private static class Node {
        Object key;
        Object value;
        Node next;

        Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashMap2 map = new MyHashMap2();

        for (int i = 0; i < 1000000; i++) {
            map.put("Key" + i, "Value" + i);
        }

        System.out.println("Size: " + map.size());

        for (int i = 0; i < 1000000; i++) {
            Object value = map.get("Key" + i);
            if (!Objects.equals(value, "Value" + i)) {
                System.out.println("Incorrect value for key Key" + i);
                break;
            }
        }
    }
}
