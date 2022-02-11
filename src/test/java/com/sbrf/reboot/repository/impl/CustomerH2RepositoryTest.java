package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() throws ClassNotFoundException {
        customerRepository = new CustomerH2Repository();
    }

    @Test
    void getAll(){
        boolean tomCreated = customerRepository.createCustomer("Tom", "tom@ya.ru");
        List<Customer> all = customerRepository.getAll();

        assertTrue(all.size() != 0);
    }

    @Test
    void createCustomerTrue(){
        boolean mariaCreated = customerRepository.createCustomer("Eva", "eva98@ya.ru");
        assertTrue(mariaCreated);
    }

    @Test
    void createCustomerFalse(){
        boolean mariaCreated = customerRepository.createCustomer("Maria", "maria98@ya.ru");
        assertFalse(mariaCreated);
    }

    @Test
    void deleteCustomerTrue(){
        boolean mariaDeleted = customerRepository.deleteCustomer(9L);
        assertTrue(mariaDeleted);
    }

    @Test
    void deleteCustomerFalse(){
        boolean mariaDeleted = customerRepository.deleteCustomer(100L);
        assertFalse(mariaDeleted);
    }

    @Test
    void updateCustomerTrue(){
        boolean mariaUpdate = customerRepository.updateCustomer(9L, "Danya", "numnums021@gmail.com");
        assertTrue(mariaUpdate);
    }

    @Test
    void getCustomer(){
        Customer newCustomer = new Customer(16L, "Danya", "numnums021@gmail.com");
        Customer getCustomer = customerRepository.getCustomer(16L);

        assertEquals(newCustomer, getCustomer);
    }

    @Test
    void getNullCustomer(){
        assertNull(customerRepository.getCustomer(100L));
    }

    @Test
    void checkFalseCustomerExists(){
        Assertions.assertFalse(customerRepository.checkCustomer("Grindevald"));
    }

    @Test
    void checkTrueCustomerExists(){
        Assertions.assertTrue(customerRepository.checkCustomer("Danya"));
    }

    @Test
    void checkFalseCustomerExists2(){
        Assertions.assertFalse(customerRepository.customerIsExist("Danya2","numnums021@gmail2.com"));
    }

    @Test
    void checkTrueCustomerExists2(){
        Assertions.assertTrue(customerRepository.customerIsExist("Danya","numnums021@gmail.com"));
    }
}