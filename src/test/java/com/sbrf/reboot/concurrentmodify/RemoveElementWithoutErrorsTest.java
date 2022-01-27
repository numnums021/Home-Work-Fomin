package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Класс тестирования для обработки исключения ConcurrentModificationException.
 * @autor Фомин Даниил (numnums021)
 * @version 1.8
 */
public class RemoveElementWithoutErrorsTest {

    /**
     * Процедура проверки вызова исключения при удалении из ArrayList
     * элемента с индексом '1'.
     */
    @Test
    public void successConcurrentModificationException() {

        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertThrows(ConcurrentModificationException.class, () -> {
            for (Integer integer : list) {
                list.remove(1);
            }
        });

    }

    /**
     * Процедура проверки правильного удаления элемента из ArrayList
     * с помощью removeIf().
     */
    @Test
    public void successRemoveElementUsingRemoveIf() {

        List<Integer> list = new ArrayList(Arrays.asList(1, 2, 3));

        List<Integer> checkList = new ArrayList(Arrays.asList(2, 3));

        list.removeIf(i -> i == 1);

        Assertions.assertEquals(list, checkList);

    }

    /**
     * Процедура проверки правильного удаления элемента из ArrayList
     * с помощью Streams.
     */
    @Test
    public void successDeleteItemUsingStreams() {

        List<Integer> list = new ArrayList(Arrays.asList(1, 2, 3));

        List<Integer> checkList = new ArrayList(Arrays.asList(2, 3));

        List<String> newList = list.stream()
                .filter(i -> i != 1)
                .map(Object::toString) // Object::toString - ссылка на метод
                .collect(toList());

        Assertions.assertTrue(() ->{
            boolean result = true; // будеем надеяться на лучшее

            // При условии, что списки уже отсортированы
            if (newList.size() == checkList.size()) {
                for (int ind = 0; ind < newList.size(); ind++) {
                    if (newList.get(ind).equals(Integer.toString(checkList.get(ind)))) {
                        result = true;
                    }
                    else {
                        result = false;
                        break;
                    }
                }
            }
            else {
                result = false;
            }
            return result;
        });

    }

    /**
     * Процедура проверки правильного удаления элемента из ArrayList
     * с помощью 'решения в лоб' посредством переноса в новый ArrayList
     */
    @Test
    public void successRemoveElementWithNewList() {

        List<Integer> list = new ArrayList(Arrays.asList(1, 2, 3));

        List<Integer> checkList = new ArrayList(Arrays.asList(2, 3));

        List<Integer> newList = new ArrayList();

        for (Integer item: list)
            if (item != 1)
                newList.add(item);

        Assertions.assertEquals(newList, checkList);

    }

}
