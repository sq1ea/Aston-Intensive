package org.example.realization.stream;

import org.example.realization.stream.entity.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMaker {

    private static final String filePath = "example.txt";
    private static final String filterWord = "example";
    static Random random = new Random();

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 30),
                new Person("Dave", 25),
                new Person("Eve", 35),
                new Person("Frank", 35),
                new Person("Grace", 40)
        );

        // Вызов метода для группировки и вычисления среднего возраста
        Map<Integer, Double> averageAges = calculateAVG(persons);
        // Выводим результат
        averageAges.forEach((age, avg) ->
                System.out.println("Age: " + age + ", Average: " + avg)
        );

        System.out.println(randoms(10));
    }

    // Генерация случайных чисел, фильтрация четных чисел и их сортировка
    public static List<Integer> randoms(int count) {
        return random.ints(count, 0, 100) // Генерация count случайных чисел от 0 до 99
                .filter(num -> num % 2 == 0) // Фильтрация четных чисел
                .sorted() // Сортировка
                .boxed() // Преобразование IntStream обратно в Stream<Integer>
                .collect(Collectors.toList()); // Сбор в список
    }

    // Преоброзование в верхний регистр
    public static List<String> toUpperCase (List <String> entry) {
        return  entry.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    // Вычисление суммы всех элементов в массиве
    public static int sumOfArray (Object[] array) {
        return  Arrays.stream(array)         // Преобразуем массив в Stream<Object>
                .mapToInt(obj -> (int) obj)  // Преобразуем каждый элемент в int
                .sum();                      // Вычисляем сумму всех элементов
    }

    // Группировкра объектов по возрасту
    public static Map<Integer, Double> calculateAVG(List<Person> entry) {
        return entry.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        TreeMap::new,
                        Collectors.averagingDouble(Person::getAge)
                ));
    }


    // Фильтрация строки более 5и символов
    public static String findByLength (List<String> entry) {
        return entry.stream()
                .filter(s -> s.length() > 5)
                .findFirst()
                .orElse("Не найдено");
    }

    // Чтение файла построчно
    public void readFile () {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.filter(line -> line.contains(filterWord))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
