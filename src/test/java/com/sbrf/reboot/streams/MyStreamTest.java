package com.sbrf.reboot.streams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс тестирования stream.
 * @autor Фомин Даниил (numnums021)
 * @version 1.11        07.02.2022
 */
class MyStreamTest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class Clients {
        private int id;
        private String name;
        private String surname;
        private double balance;
        private Date date;
    }

    private final List<Clients> clientsList = Arrays.asList(
            new Clients(0, "Daniil", "Fomin", 1000, new Date(1, Calendar.FEBRUARY, 1999)),
            new Clients(10, "Eva", "Alexeeva", 2000, new Date(22, Calendar.JANUARY, 1996)),
            new Clients(30, "Ivan", "Jokers", 3000, new Date(1, Calendar.FEBRUARY, 1999)),
            new Clients(50, "Eva", "Kalopser", 3000, new Date(29, Calendar.AUGUST, 2000))
            );

    /**
     * Метод, проверяющий правильность группировки списка по имени клиента
     * (деление на списки).
     */
    @Test
    void clientsListGroupByNameToMap() {

        List<Clients> answerClientsList = Arrays.asList(
                new Clients(10, "Eva", "Alexeeva", 2000, new Date(22, Calendar.JANUARY, 1996)),
                new Clients(50, "Eva", "Kalopser", 3000, new Date(29, Calendar.AUGUST, 2000))
        );

        Map<String, List<Clients>> clientsMap = clientsList.stream()
                .collect(Collectors.groupingBy(Clients::getName));

        Assertions.assertEquals(answerClientsList, clientsMap.get("Eva"));

    }

    /**
     * Метод, проверяющий правильность группировки списка клиентов по дате
     * (деление на множества)
     */
    @Test
    void clientsListGroupByDateToHashSet() {

        Set<Clients> clientsSet = new HashSet<Clients>(){{
            add(new Clients(0, "Daniil", "Fomin", 1000, new Date(1, Calendar.FEBRUARY, 1999)));
            add(new Clients(30, "Ivan", "Jokers", 3000, new Date(1, Calendar.FEBRUARY, 1999)));
        }};

        Map<Date, Set<Clients>> clientsMap = clientsList.stream()
                .collect(Collectors.groupingBy(Clients::getDate, Collectors.toSet()));

        Assertions.assertEquals(clientsSet, clientsMap.get(new Date(1, Calendar.FEBRUARY, 1999)));
    }

    /**
     * Метод, проверяющий группировку списка клиентов по балансу,
     * клиенты представлены только именами единой строкой.
     */
    @Test
    void clientsListGroupByBalanceToMap() {

        String answerString = "{Ivan, Eva}";

        Map<Double, String> clientsMap = clientsList.stream()
                .collect(Collectors.groupingBy(Clients::getBalance,
                        Collectors.mapping(Clients::getName,
                                Collectors.joining(", ", "{","}"))));

        Assertions.assertEquals(answerString, clientsMap.get(3000.0));

    }

    /**
     * Метод, проверяющий сумму баланса всех клиентов.
     */
    @Test
    void sumAllBalanceFromClientsList() {

        double answer = 9000.0;

        double sum = clientsList.stream().mapToDouble(Clients::getBalance).sum();

        Assertions.assertEquals(answer, sum);

    }

    /**
     * Метод,проверяющий сортировку клиентов в обратном порядке.
     */
    @Test
    void reverseOrderClientsList() {

        List<Clients> answerListClients = Arrays.asList(
                new Clients(30, "Ivan", "Jokers", 3000, new Date(1, Calendar.FEBRUARY, 1999)),
                new Clients(10, "Eva", "Alexeeva", 2000, new Date(22, Calendar.JANUARY, 1996)),
                new Clients(50, "Eva", "Kalopser", 3000, new Date(29, Calendar.AUGUST, 2000)),
                new Clients(0, "Daniil", "Fomin", 1000, new Date(1, Calendar.FEBRUARY, 1999))
        );

        List<Clients> newClientsList = clientsList.stream().
                sorted((o1,o2) -> -o1.getName().compareTo(o2.getName())).
                collect(Collectors.toList());

        Assertions.assertEquals(answerListClients, newClientsList);

    }

}
