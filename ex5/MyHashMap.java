package hw9.ex5;

public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] buckets;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity: " + capacity);
        }
        buckets = new Node[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> newNode = new Node<>(key, value);

        Node<K, V> currentNode = buckets[bucketIndex];
        if (currentNode == null) {
            buckets[bucketIndex] = newNode;
            size++;
        } else {
            Node<K, V> prevNode = null;
            while (currentNode != null) {
                if (currentNode.key.equals(key)) {
                    currentNode.value = value;
                    return;
                }
                prevNode = currentNode;
                currentNode = currentNode.next;
            }
            prevNode.next = newNode;
            size++;
        }
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> currentNode = buckets[bucketIndex];
        Node<K, V> prevNode = null;

        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (prevNode == null) {
                    buckets[bucketIndex] = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                size--;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> currentNode = buckets[bucketIndex];

        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }

        return null;
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % buckets.length;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        // Putting key-value pairs
        hashMap.put("Apple", 10);
        hashMap.put("Banana", 5);
        hashMap.put("Orange", 7);

        // Getting size
        System.out.println("Size: " + hashMap.size());

        // Getting values by keys
        System.out.println("Value for 'Apple': " + hashMap.get("Apple"));
        System.out.println("Value for 'Banana': " + hashMap.get("Banana"));
        System.out.println("Value for 'Orange': " + hashMap.get("Orange"));

        // Removing a key-value pair
        hashMap.remove("Banana");

        // Clearing the collection
        hashMap.clear();

        // Getting size after removing and clearing
        System.out.println("Size: " + hashMap.size());
    }
}

