package com.sbrf.reboot.functionalinterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FunctionalInterfaceTest {

    /**
     * Класс некоторого объекта, содержит 1 поле - имя объекта.
     * С помощью аннотаций lombok реализованы базовые методы класса.
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SomeObject {
        private String objectName;
    }

    /**
     * Функциональный интерфейс.
     */
    @FunctionalInterface
    interface ObjectToJsonFunction<T> {
        String applyAsJson(T t) throws JsonProcessingException;
    }

    /**
     * Класс  ListConverter с единственным методом toJsonsList,
     * который принимает на вход коллекцию объектов SomeObject
     * и функциональный интерфейс ObjectToJsonFunction
     */
    static class ListConverter<T> {
        /**
         * Метод преобразует коллекцию SomeObject объектов,
         * в коллекцию с Json этих же объектов, для передачи,
         * например, во фронтальную систему.
         */
        public List<String> toJsonsList(@NonNull List<T> someObjects, ObjectToJsonFunction<T> objectToJsonFunction) {

            List<String> result = new ArrayList<>();

            if (someObjects.isEmpty())
                throw new IllegalArgumentException("The list is empty");

            for (T element : someObjects) {
                try {
                    result.add(objectToJsonFunction.applyAsJson(element));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }
    }

    /**
     * Тестовый класс для проверки
     */
    @Test
    public void successCallFunctionalInterface() {
        ListConverter<SomeObject> ListConverter = new ListConverter<>();

        ObjectToJsonFunction<SomeObject> objectToJsonFunction = someObject -> new ObjectMapper().writeValueAsString(someObject);

        List<String> strings = ListConverter.toJsonsList(
                Arrays.asList(
                        new SomeObject("Object-1"),
                        new SomeObject("Object-2")
                ),
                objectToJsonFunction
        );

        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-1\"}"));
        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-2\"}"));
    }

}
