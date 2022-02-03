package com.sbrf.reboot.functionalinterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.*;


public class MyFunctionalInterfaceTest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class Ninja {
        private String name;
        private int power;
    }

   @FunctionalInterface
   interface myPredicate <T>{
       T apply(T t1, T t2);
   }

   @Test
   public void success() {
       myPredicate<Ninja> ninjaMyPredicate = (ninja1, ninja2) -> ninja1.power > ninja2.power ? ninja1 : ninja2;

       Ninja ninja1 = new Ninja("Naruto", 777);
       Ninja ninja2 = new Ninja("Saske", 666);

       Assertions.assertEquals(ninja1, ninjaMyPredicate.apply(ninja1,ninja2));
   }

   @Test
   public void isNotZero() {
       Predicate<Integer> isNotZero = value -> value != 0;
       Assertions.assertTrue(isNotZero.test(4));
   }

   @Test
   public void successComparison() {
       Predicate<Integer> predicate = x -> x > 5;
       Assertions.assertTrue(predicate.test(7));
   }

   @Test
   public void successFunction() {
       Function<Integer, String> function = Object::toString;
       Assertions.assertEquals("1703", function.apply(1703));
   }

    @Test
    public void successUnaryOperator() {
        UnaryOperator<Double> unaryOperator = Math::sqrt;
        Assertions.assertEquals(5, unaryOperator.apply(25.0));
    }

    @Test
    public void successBinaryOperator() {
        BinaryOperator<Double> binaryOperator = Math::pow;
        Assertions.assertEquals(25, binaryOperator.apply(5.0, 5.0));
    }

}
