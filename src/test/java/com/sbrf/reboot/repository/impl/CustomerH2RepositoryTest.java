package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() throws ClassNotFoundException {
        customerRepository = new CustomerH2Repository();
    }

    @Test
    void getAll() throws SQLException {
        // boolean tomCreated = customerRepository.createCustomer("Tom", "tom@ya.ru");

        List<Customer> all = customerRepository.getAll();

        assertTrue(all.size() != 0);
    }

    @SneakyThrows
    @Test
    void createCustomer() {

        boolean mariaCreated = customerRepository.createCustomer("Maria", "maria98@ya.ru");

        assertTrue(mariaCreated);
    }
}