package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public boolean isClientHasContract(@NonNull long clientId, @NonNull long contractNumber) {
        if (accountRepository.getAllContractsByClientId(clientId).contains(contractNumber))
            return true;
        return false;
    }

    public boolean isBankBranch(@NonNull long bankBranchId, @NonNull long contractNumber) {
        if (accountRepository.getBanksByContractNumber(contractNumber).contains(bankBranchId))
            return true;
        return false;
    }

}