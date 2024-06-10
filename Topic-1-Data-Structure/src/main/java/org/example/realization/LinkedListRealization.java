package org.example.realization;

public class LinkedListRealization <T> {
    private NodeRealization<T> head;
    private int size;

    public LinkedListRealization() {
        head = null;
        size = 0;
    }

    public void add(T data) {
        NodeRealization<T> newNode = new NodeRealization<>(data);
        if (head == null) {
            head = newNode;
        } else {
            NodeRealization<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        NodeRealization<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T remove(int index) {
        checkIndex(index);
        NodeRealization<T> current = head;
        if (index == 0) {
            head = head.next;
        } else {
            NodeRealization<T> previous = null;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
        }
        size--;
        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}


