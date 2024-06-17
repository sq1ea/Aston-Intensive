package org.example.realization.stream;

import org.example.realization.stream.entity.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMaker {

    private static final String filePath = "example.txt";
    private static final String filterWord = "example";
    static Random random = new Random();

    public static void main(String[] args) {
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

    public static List<String> toUpperCase (List <String> entry) {
        return  entry.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static int sumOfArray (Object[] array) {
        return  Arrays.stream(array)         // Преобразуем массив в Stream<Object>
                .mapToInt(obj -> (int) obj)  // Преобразуем каждый элемент в int
                .sum();                      // Вычисляем сумму всех элементов
    }

    public static void calculateAVG (List<Person> entry) {
        Map<Integer, Double> averageAgeByAgeGroup =
                entry.stream()
                        .collect(Collectors.groupingBy
                                (Person::getAge, Collectors.averagingDouble(Person::getAge)));

        averageAgeByAgeGroup.forEach((age, averageAge) -> {
            System.out.println("Возрастрная группа: " + age + ", Средний возраст: " + averageAge);
        });
    }

    public static String findByLength (List<String> entry) {
        return entry.stream()
                .filter(s -> s.length() > 5)
                .findFirst()
                .orElse("Не найдено");
    }

    public void readFile () {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.filter(line -> line.contains(filterWord))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
