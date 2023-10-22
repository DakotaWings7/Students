package org.students;

public class MyHashMap<K, V> {
    private Node<K, V>[] buckets;
    private int capacity = 16;

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
            return next != null;
        }
    }

    public void arrayCopy(Person[] newPersons, Person[] persons) {
        int len;
        if (newPersons.length < persons.length) {
            len = newPersons.length;
        } else {
            len = persons.length;
        }
        for (int i = 0; i < len; i++) {
            newPersons[i] = persons[i];
        }
    }

    public Person[] reallocArray(Person[] persons) {
        Person[] newPersons = new Person[(int) (persons.length * 1.5)];

        arrayCopy(newPersons, persons);

        return newPersons;
    }

    public MyHashMap() {
        buckets = new Node[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public void realloc(int newSize) {
        Node[] newBuckets = new Node[newSize + 1];

        for (int i = 0; i < capacity; ++i) {
            newBuckets[i] = buckets[i];
        }
        capacity = newBuckets.length;
        buckets = newBuckets;
    }

    public void put(K key, Person value) {
        int index = hash(key);

        if (index >= capacity) {
            realloc(index);
        }

        if (buckets[index] == null) {
            buckets[index] = new Node(key, value, null);
            buckets[index].tail = buckets[index];
        } else {
            /* Очень неэффективно
            Node current = buckets[index];

            while (current.hasNext()) {
                current = current.next;
            }

            current.next = new Node<K, V>(key, (V) value, null);*/
            buckets[index].tail.next = new Node<K, V>(key, (V) value, null);
            buckets[index].tail = buckets[index].tail.next;
        }
    }

    public Person[] get(K key) {
        Person[] persons = new Person[100];

        int index = hash(key);
        Node current = buckets[index];
        int i = 0;
        while (current != null) {
            if (persons.length <= i) {
                persons = reallocArray(persons);
            }
            persons[i++] = (Person) current.value;
            current = current.next;
        }
        Person[] noNullPersons = new Person[i];
        arrayCopy(noNullPersons, persons);

        return noNullPersons;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < capacity; ++i) {
            Node current = buckets[i];
            if (buckets[i] != null) {
                result.append(i)
                        .append(": ");
            }
            while (current != null) {
                result.append("[")
                        .append(current.key)
                        .append(": ")
                        .append(current.value.toString())
                        .append("]");
                current = current.next;
            }
            if (buckets[i] != null) {
                result.append("\n");
            }
        }

        return result.toString();
    }
    private int hash(K key) {
        if (key instanceof String) {
            return key.hashCode() - "А".hashCode();
        }
        return key.hashCode();
    }
}