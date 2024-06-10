package org.example.realization;


import java.util.Arrays;
import java.util.Collection;

public class ArrayListRealization<T> {

    private static  final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayListRealization() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayListRealization(int initialCapacity) {

/*
    Конструктор с пользовательской начальной емкостью:
    Позволяет задать начальную емкость массива, проверяя её на отрицательное значение.
*/

        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elements = (T[]) new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayListRealization(Collection<? extends T> collection) {

/*
    Конструктор из другой коллекции:  Принимает коллекцию, копирует её элементы в массив
    и добавляет их в список.
*/

        elements = (T[]) new Object[collection.size()];
        for (T element : collection) {
            add(element);
        }
    }

    public T remove (int index) {

/*
    1. Проверка индекса: Проверяем, находится ли индекс в допустимых пределах.
    2. Удаление элемента: Сохраняем удаляемый элемент.
    3. Смещение элементов: Если после удаляемого элемента есть другие элементы, смещаем их влево.
    4. Очистка последнего элемента: Уменьшаем размер и освобождаем последний элемент, чтобы избежать утечки памяти.
    5. Возвращение удаленного элемента: Возвращаем удаленный элемент.
*/

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Уменьшаем размер и освобождаем последний элемент
        return removedElement;
    }


    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void getAll () {
        int count = 0;
        while (elements[count] != null) {
            System.out.println(elements[count]);
            count++;
        }
    }

    public void add (T obj) {
        if (size == elements.length) {
            increaseCapacity();
        } else elements[size++] = obj;
    }

    public T get (int index) {
        return elements[index];
    }

    public int size () {
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }
}
