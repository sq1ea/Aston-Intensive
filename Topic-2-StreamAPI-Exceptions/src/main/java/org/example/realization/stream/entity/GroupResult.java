package org.example.realization.stream.entity;

import java.util.List;

public class GroupResult {

    // Вспомогательный класс для хранения результатов группировки

    List<Person> people;
    double averageAge;

    public GroupResult(List<Person> people, double averageAge) {
        this.people = people;
        this.averageAge = averageAge;
    }

    @Override
    public String toString() {
        return "Люди: " + people + ", Средний возраст: " + averageAge;
    }
}
