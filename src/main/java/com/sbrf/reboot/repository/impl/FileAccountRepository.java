package com.sbrf.reboot.repository.impl;
import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class FileAccountRepository implements AccountRepository {

    private final String PATH;
    private final String ARRAY_SYMBOL = "[/,:\"number clientId]";

    @Override
    public void updateClientContract(long clientId, long oldContractNumber, long newContractNumber) throws FileNotFoundException {
        StringBuilder textForOutput = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader (new FileReader(PATH))) {
            String lineFromFile;
            boolean flag = false;
            while((lineFromFile = bufferedReader.readLine()) != null) {
                if (flag) {
                    textForOutput.append(lineFromFile.replaceAll("" + oldContractNumber, "" + newContractNumber)).append("\n");
                    flag = false;
                    continue;
                }
                if (checkLineOnClientID(lineFromFile, clientId)) {
                    flag = true;
                }
                textForOutput.append(lineFromFile).append("\n");
            }
        }
        catch(IOException ex) {
            throw new FileNotFoundException();
        }
        writeTextInFile(textForOutput);
    }
    public void writeTextInFile(StringBuilder textForWriter) throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH))) {
            writer.write(textForWriter.toString());
        }
        catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public Set<Long> getAllContractsByClientId(@NonNull long clientId) throws FileNotFoundException{
        Set<Long> actualContracts = new HashSet<>();

        try (BufferedReader bufferedReader = new BufferedReader (new FileReader(PATH))) {
            String lineFromFile;
            boolean flag = false;
            while((lineFromFile = bufferedReader.readLine()) != null){
                if (flag) {
                    actualContracts.add(Long.parseLong(lineFromFile.replaceAll(ARRAY_SYMBOL, "")));
                    flag = false;
                }
                if (checkLineOnClientID(lineFromFile, clientId)) {
                    flag = true;
                }
            }
        }
        catch(IOException ex) {
            throw new FileNotFoundException();
        }

        return actualContracts;
    }

    public boolean checkLineOnClientID(String line, long clientId){
        return line.contains("clientId") && Long.parseLong(line.replaceAll(ARRAY_SYMBOL, "")) == clientId;
    }



    @Override
    public Set<Long> getBanksByContractNumber(long contractNumber) {
        return null;
    }
}
