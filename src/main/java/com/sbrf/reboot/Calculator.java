package com.sbrf.reboot;

public class Calculator {

    public int getAddition(int i, int i1) {
        return i+i1;
    }

    public int getSubtraction(int i, int i1) {
        return i-i1;
    }

    public int getMultiplication(int i, int i1) {
        return i*i1;
    }

    public int getDivision(int i, int i1) {
        return i/i1;
    }

    public double getExponentiation(int i, int i1){
        return Math.pow(i, i1);
    }

    public double getRoot(int i){
        return Math.sqrt(i);
    }

    public int getMax(int i, int j) {
        return Math.max(i, j);
    }

}
