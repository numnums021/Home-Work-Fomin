package com.sbrf.reboot;

import com.sbrf.reboot.repository.impl.FileAccountRepository;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileAccountRepository fileAccountRepository = new FileAccountRepository("src/main/resources/Accounts.txt");
        fileAccountRepository.getAllContractsByClientId(1);
    }
}
