package org.example.realization.stream;

import org.example.realization.stream.entity.GroupResult;
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
        // Список списков объектов Person
        List<List<Person>> listOfGroups = Arrays.asList(
                Arrays.asList(
                        new Person("Frank", 40),
                        new Person("Grace", 30)),
                Arrays.asList(
                        new Person("Charlie", 30),
                        new Person("David", 25),
                        new Person("Eve", 40)),
                Arrays.asList(
                        new Person("Alice", 30),
                        new Person("Bob", 25)));

        // Получаем результат для каждого списка групп и выводим результат
        List<GroupResult> groupResults = groupByAgeAndCalculateAverage(listOfGroups);
        groupResults.forEach(System.out::println);


        System.out.println(randoms(10));

        List<String> input = Arrays.asList("123", "456", "abc", "789", "def");

        List<Integer> filteredNumbers = filterAndConvertStream(input);

        System.out.println(filteredNumbers);

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

    // Метод для группировки и вычисления среднего возраста
    public static List<GroupResult> groupByAgeAndCalculateAverage(List<List<Person>> listOfGroups) {
        return listOfGroups.stream()
                .map(group -> new GroupResult(
                        group,
                        group.stream().collect(Collectors.averagingInt(person -> person.age))
                ))
                .collect(Collectors.toList());
    }


    // Фильтрация строки более 5и символов
    public static String findByLength (List<String> entry) {
        return entry.stream()
                .filter(s -> s.length() > 5)
                .findFirst()
                .orElse("Не найдено");
    }

    // Фильтрация с исключением некорректного значения
    private static List<Integer> filterAndConvertStream(List<String> input) {
        return input.stream()
                .filter(str -> {
                    try {
                        Integer.parseInt(str);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    // Чтение файла построчно
    public static void readFile () {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.filter(line -> line.contains(filterWord))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFileParallel(String filePath, String filterWord) {
        try (Stream<String> lines = Files.lines(Paths.get(StreamMaker.filePath))) {
            lines.parallel()
                    .filter(line -> line.contains(StreamMaker.filterWord))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
