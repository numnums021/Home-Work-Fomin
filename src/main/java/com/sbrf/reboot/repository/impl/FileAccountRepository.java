package com.sbrf.reboot.repository.impl;
import com.sbrf.reboot.repository.AccountRepository;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileAccountRepository implements AccountRepository {

    final String FILE_ADDRESS = "C://Users//User//IdeaProjects//Home-Work-Fomin3//src//main//resources//Accounts.txt";

    @Override
    public Set<Long> getAllContractsByClientId(long clientId) {
        String fileText = "";

        try(FileInputStream fin = new FileInputStream(FILE_ADDRESS))
        {
            int i = -1;

            while((i = fin.read()) != -1) {
                fileText = fileText + "" + (char)i;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(fileText);

        Set<Long> actualAccounts = new HashSet<>();

        return actualAccounts;
    }

    @Override
    public Set<Long> getBanksByContractNumber(long contractNumber) {
        return null;
    }
}
