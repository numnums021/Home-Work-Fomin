package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() throws ClassNotFoundException {
        customerRepository = new CustomerH2Repository();
    }

    @Test
    void getAll() throws SQLException {
        boolean tomCreated = customerRepository.createCustomer("Tom", "tom@ya.ru");
        List<Customer> all = customerRepository.getAll();

        assertTrue(all.size() != 0);
    }

    @Test
    void createCustomer() throws SQLException {

        boolean mariaCreated = customerRepository.createCustomer("Maria", "maria98@ya.ru");

        assertTrue(mariaCreated);
    }

    @Test
    void deleteCustomer() throws SQLException {

        boolean mariaDeleted = customerRepository.deleteCustomer(15L);

        assertTrue(mariaDeleted);
    }

    @Test
    void updateCustomer() throws SQLException {

        boolean mariaUpdate = customerRepository.updateCustomer(9L, "Danya", "numnums021@gmail.com");

        assertTrue(mariaUpdate);
    }

    @Test
    void getCustomer() throws SQLException {

        Customer newCustomer = new Customer(16L, "Danya", "numnums021@gmail.com");
        Customer getCustomer = customerRepository.getCustomer(16L);

        assertEquals(newCustomer, getCustomer);
    }

    @Test
    void checkFalseCustomerExists() throws SQLException {
        Assertions.assertFalse(customerRepository.checkCustomer("Grindevald"));
    }

    @Test
    void checkTrueCustomerExists() throws SQLException {
        Assertions.assertTrue(customerRepository.checkCustomer("Danya"));
    }
}