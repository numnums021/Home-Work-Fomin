package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);

        accountService = new AccountService(accountRepository);
    }

    @SneakyThrows
    @Test
    void contractExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(111L);

        long clientId = 1L;
        long contractNumber = 111L;


        when(accountRepository.getAllContractsByClientId(clientId)).thenReturn(accounts);

        assertTrue(accountService.isClientHasContract(clientId, contractNumber));
    }

    @SneakyThrows
    @Test
    void contractNotExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(222L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllContractsByClientId(clientId)).thenReturn(accounts);

        assertFalse(accountService.isClientHasContract(clientId, contractNumber));
    }

    @SneakyThrows
    @Test
    void bankBranchExist() {
        Set<Long> banks = new HashSet();
        banks.add(1001L);

        long bankBranch = 1001L;
        long contractNumber = 111L;

        when(accountRepository.getBanksByContractNumber(contractNumber)).thenReturn(banks);

        assertTrue(accountService.isBankBranch(bankBranch, contractNumber));
    }

    @SneakyThrows
    @Test
    void bankBranchNotExist() {
        Set<Long> banks = new HashSet();
        banks.add(1000L);

        long bankBranch = 1001L;
        long contractNumber = 111L;

        when(accountRepository.getBanksByContractNumber(contractNumber)).thenReturn(banks);

        assertFalse(accountService.isBankBranch(bankBranch, contractNumber));
    }

    @Test
    void repositoryHasTreeMethods() {
        assertEquals(2, AccountRepository.class.getMethods().length);
    }

    @Test
    void serviceHasTreeMethods() {
        assertEquals(3, AccountService.class.getMethods().length - Object.class.getMethods().length);
    }

}