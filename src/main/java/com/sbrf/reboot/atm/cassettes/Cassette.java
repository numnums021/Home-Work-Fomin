package com.sbrf.reboot.atm.cassettes;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/*
Класс Cassette принимает на вход только банкноты, тк <T extends Banknote> - ограничивает верхнюю границу классом Banknote
getCountBanknotes - возвращает количество банкнот
*/
@NoArgsConstructor
public class Cassette <T extends Banknote>{

    private ArrayList<T> banknoteList;

    public Cassette(ArrayList<T> banknoteList) {
        this.banknoteList = banknoteList;
    }

    public int getCountBanknotes() {
        return banknoteList.size();
    }

    public static <T> T getValue(Object obj, Class<T> tClass) {
        return (T) obj;
    }

    public static <T> T getValue(Object obj){
            return (T) obj;
    }

}
