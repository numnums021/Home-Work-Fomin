package com.sbrf.reboot.functionalinterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;

/**
 * Класс тестирования функционального интерфейса.
 * @autor Фомин Даниил (numnums021)
 * @version 1.10        03.02.2022
 */
public class MyFunctionalInterfaceTest {

    /**
     * Класс "ниндзя", содержит 2 поля - имя ниндзя и его силу.
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class Ninja {
        private String name;
        private int power;
    }

    /**
     * Функциональный интерфейс
     */
   @FunctionalInterface
   interface myFunction <T>{
       T apply(T t1, T t2);
   }

    /**
     * Тестовый метод, проверяющий правильность сравнения сил двух ниндзя.
     */
   @Test
   public void successfulNinjaPowerCheck() {
       myFunction<Ninja> ninjaMyPredicate = (ninja1, ninja2) -> ninja1.power > ninja2.power ? ninja1 : ninja2;

       Ninja ninja1 = new Ninja("Naruto", 777);
       Ninja ninja2 = new Ninja("Saske", 666);

       Assertions.assertEquals(ninja1, ninjaMyPredicate.apply(ninja1,ninja2));
   }

    /**
     * Тестовый метод, проверяющий правильность сравнения с 0.
     */
   @Test
   public void isNotZero() {
       Predicate<Integer> isNotZero = value -> value != 0;
       Assertions.assertTrue(isNotZero.test(4));
   }

    /**
     * Тестовый метод, проверяющий правильность приведения типа Integer к String.
     */
   @Test
   public void successFunction() {
       Function<Integer, String> function = Object::toString;
       Assertions.assertEquals("1703", function.apply(1703));
   }

    /**
     * Тестовый метод, проверяющий правильность получения квадратного корня из числа.
     */
    @Test
    public void successUnaryOperator() {
        UnaryOperator<Double> unaryOperator = Math::sqrt;
        Assertions.assertEquals(5, unaryOperator.apply(25.0));
    }

    /**
     * Тестовый метод, проверяющий правильность возведение числа в степень другого числа.
     */
    @Test
    public void successBinaryOperator() {
        BinaryOperator<Double> binaryOperator = Math::pow;
        Assertions.assertEquals(25, binaryOperator.apply(5.0, 5.0));
    }

}
