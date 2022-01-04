package com.sbrf.reboot.repository.impl;
import com.sbrf.reboot.repository.AccountRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileAccountRepository implements AccountRepository {

    String PATH = "src/main/resources/Accounts.txt";

    public FileAccountRepository(String filePath) {
        this.PATH = filePath;
    }

    @Override
    public Set<Long> getAllContractsByClientId(long clientId) throws FileNotFoundException{
        ArrayList<String> accountsList = getAccountsListFromFile();
        Set<Long> actualContracts = getAccountByClientId(clientId, accountsList);

        System.out.println(actualContracts);

        return actualContracts;
    }

    public Set<Long> getAccountByClientId(long clientId, ArrayList<String> accountsList) {
        Set<Long> actualContracts = new HashSet<>();

        for (int i = 0; i < accountsList.size(); i++) {
            if (String.valueOf(clientId).equals(accountsList.get(i).substring(accountsList.get(i).lastIndexOf("Id") + 2, accountsList.get(i).indexOf("nu")))) {
                Long selectContract = Long.parseLong(accountsList.get(i).substring(accountsList.get(i).lastIndexOf("er") + 2, accountsList.get(i).length()));
                actualContracts.add(selectContract);
            }
        }

        return actualContracts;
    }

    public ArrayList<String> getAccountsListFromFile() throws FileNotFoundException {
        String fileText = "";
        ArrayList<String> accountsList = new ArrayList<>();
        try (FileInputStream fin = new FileInputStream(PATH)) {
            int i = -1;
            int flag = 0;
            while ((i = fin.read()) != -1) {
                char tmpChar = (char) i;
                if ("{".equals(String.valueOf(tmpChar))) {
                    flag = 1;
                    continue;
                }
                if ("}".equals(String.valueOf(tmpChar))) flag = 0;
                if (flag == 1 && !String.valueOf(tmpChar).equals(" ")  && !String.valueOf(tmpChar).equals("\n") && !String.valueOf(tmpChar).equals("\r")
                        && !String.valueOf(tmpChar).equals(",") && !String.valueOf(tmpChar).equals("\"")&&!String.valueOf(tmpChar).equals(":"))
                    fileText = fileText + "" + tmpChar;
                else if (flag == 0 &&!fileText.equals("")) {
                    accountsList.add(fileText);
                    fileText = "";
                }
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Неправильно введён путь до файла!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accountsList;
    }



    @Override
    public Set<Long> getBanksByContractNumber(long contractNumber) {
        return null;
    }
}
