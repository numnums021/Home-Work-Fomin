package com.sbrf.reboot;

import com.sbrf.reboot.repository.impl.FileAccountRepository;

public class Main {
    public static void main(String[] args) {
        FileAccountRepository fileAccountRepository = new FileAccountRepository();
        fileAccountRepository.getAllContractsByClientId(1);
    }
}
