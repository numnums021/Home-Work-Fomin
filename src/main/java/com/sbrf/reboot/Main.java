package com.sbrf.reboot;

import com.sbrf.reboot.atm.cassettes.Banknote;
import com.sbrf.reboot.atm.cassettes.Cassette;
import com.sbrf.reboot.repository.impl.FileAccountRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Cassette cassette = new Cassette();
        List list = Arrays.asList("Text1", "1");
        for (Object el : list) {
            String data = cassette.getValue(el);
            System.out.println(data);
        }


    }
}
