package org.example.realization;

public class NodeRealization <T> {
        T data;
        NodeRealization<T> next;

         NodeRealization(T data) {
            this.data = data;
            this.next = null;
        }
    }

