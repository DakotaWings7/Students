package org.students.homework1;

public class MyHashMap<K, V> {
    private Node<K, V>[] buckets;
    private int capacity = 16;

    public Person[] reallocArray(Person[] persons) {
        Person[] newPersons = new Person[(int)((double)persons.length * 1.5)];
        System.arraycopy(persons, 0, newPersons, 0, persons.length);
        return newPersons;
    }

    public MyHashMap() {
        this.buckets = new Node[this.capacity];
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void realloc(int newSize) {
        Node[] newBuckets = new Node[newSize + 1];
        System.arraycopy(this.buckets, 0, newBuckets, 0, this.capacity);
        this.capacity = newBuckets.length;
        this.buckets = newBuckets;
    }

    public void put(K key, Person value) {
        int index = this.hash(key);
        if (index >= this.capacity) {
            this.realloc(index);
        }

        if (this.buckets[index] == null) {
            this.buckets[index] = new Node(key, value, (Node)null);
            this.buckets[index].tail = this.buckets[index];
        } else {
            this.buckets[index].tail.next = new Node(key, value, (Node)null);
            this.buckets[index].tail = this.buckets[index].tail.next;
        }

    }

    public Person[] get(K key) {
        Person[] persons = new Person[100];
        int index = this.hash(key);
        Node current = this.buckets[index];

        int i;
        for(i = 0; current != null; current = current.next) {
            if (persons.length <= i) {
                persons = this.reallocArray(persons);
            }

            persons[i++] = (Person)current.value;
        }

        Person[] noNullPersons = new Person[i];
        System.arraycopy(persons, 0, noNullPersons, 0, Math.min(noNullPersons.length, persons.length));
        return noNullPersons;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("");

        for(int i = 0; i < this.capacity; ++i) {
            Node current = this.buckets[i];
            if (this.buckets[i] != null) {
                result.append(i).append(": ");
            }

            while(current != null) {
                result.append("[").append(current.key).append(": ").append(current.value.toString()).append("]");
                current = current.next;
            }

            if (this.buckets[i] != null) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    private int hash(K key) {
        return key instanceof String ? key.hashCode() - "А".hashCode() : key.hashCode();
    }

    static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> tail;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = key.hashCode();
        }

        public boolean hasNext() {
            return this.next != null;
        }
    }
}