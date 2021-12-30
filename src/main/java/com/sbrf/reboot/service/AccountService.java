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
        return accountRepository.getAllContractsByClientId(clientId).contains(contractNumber);
    }

    public boolean isBankBranch(@NonNull long bankBranchId, @NonNull long contractNumber) {
        return accountRepository.getBanksByContractNumber(contractNumber).contains(bankBranchId);
    }

}