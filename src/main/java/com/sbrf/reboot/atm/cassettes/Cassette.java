package com.sbrf.reboot.atm.cassettes;

import java.util.ArrayList;

/*
Класс Cassette принимает на вход только банкноты
getCountBanknotes - возвращает количество банкнот
*/
public class Cassette <T extends Banknote>{
    private ArrayList<T> banknote;

    public Cassette(ArrayList<T> banknote) {
        this.banknote = banknote;
    }

    public int getCountBanknotes() {
        return banknote.size();
    }

}
