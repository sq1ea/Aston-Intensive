package org.example.context;

import org.example.component.IntensiveComponent;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class IntensiveContext {
    private final Map<Class<?>, Object> instances = new HashMap<>();
    private final String basePackage;

    // Конструктор для задания базового пакета
    public IntensiveContext(String basePackage) {
        this.basePackage = basePackage;
    }

    // Получение объекта заданного типа из контекста
    public <T> T getObject(Class<T> type) {
        try {
            // Возвращаем сохраненный экземпляр, если он уже есть
            if (instances.containsKey(type)) {
                return type.cast(instances.get(type));
            }

            // Находим и создаем новый экземпляр класса
            Class<?> implClass = findImplementation(type);
            if (implClass == null) {
                throw new RuntimeException();
            }
            T instance = type.cast(implClass.getDeclaredConstructor().newInstance());
            instances.put(type, instance);

            // Инъекция зависимостей для полей с аннотацией @IntensiveComponent
            for (Field field : implClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(IntensiveComponent.class)) {
                    Object dependency = getObject(field.getType());
                    field.setAccessible(true);
                    field.set(instance, dependency);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // Поиск реализации заданного типа в базовом пакете
    private Class<?> findImplementation(Class<?> type) {
        try {
            String packageName = basePackage + "." + type.getSimpleName();
            return Class.forName(packageName);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
