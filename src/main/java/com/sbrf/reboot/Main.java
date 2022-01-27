package com.sbrf.reboot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList(Arrays.asList(1, 2, 3));

        List<Integer> checkList = new ArrayList(Arrays.asList(2, 3));

        List<String> newList = list.stream()
                .filter(i -> i != 1)
                .map(Object::toString) // Object::toString - ссылка на метод
                .collect(Collectors.toList());

        System.out.println("checkList: " + checkList);
        System.out.println("newList: " + newList);
        System.out.println(checkList.getClass() + " - " + newList.getClass());
        System.out.println(checkList.size() + " - " + newList.size());

        if (newList.size() == checkList.size()) {
            for (int ind = 0; ind < newList.size(); ind++) {
                if (newList.get(ind).equals(Integer.toString(checkList.get(ind)))){
                     System.out.println("true");
                }
            }
        }

//        System.out.println(Objects.equals(checkList,newList));
//        System.out.println(checkList.equals(newList));
//        System.out.println(checkList.containsAll(newList));
//        System.out.println(newList.equals(checkList.toString()));
//        System.out.println(newList.stream().allMatch(checkList.get(0)::equals));
//        System.out.println(!Collections.disjoint(checkList, newList));
//        System.out.println(checkList.stream().anyMatch(newList::contains));
//        System.out.println(checkList.equals(newList));
//        System.out.println(IntStream.range(0, checkList.size()).allMatch(i -> false));
    }
}
