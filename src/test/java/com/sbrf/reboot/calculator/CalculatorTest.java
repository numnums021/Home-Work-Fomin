package com.sbrf.reboot.calculator;

import com.sbrf.reboot.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void getAddition() {
        assertEquals(9, calculator.getAddition(4, 5));
    }

    @Test
    void getSubtraction() {
        assertEquals(-1, calculator.getSubtraction(4, 5));
    }

    @Test
    void getMultiplication() {
        assertEquals(20, calculator.getMultiplication(4, 5));
    }

    @Test
    void getDivision() {
        assertEquals(3, calculator.getDivision(9, 3));
    }

    @Test
    void getExponentiation() {
        assertEquals(128, calculator.getExponentiation(2, 7));
    }

    @Test
    void getRoot() {
        assertEquals(3, calculator.getRoot(9));
    }

    @Test
    void getMax() {
        assertEquals(6, calculator.getMax(6, 5));
    }


    @Test
    void classHasSevenMethods() {
        assertEquals(7, Calculator.class.getMethods().length - Object.class.getMethods().length);
    }
}