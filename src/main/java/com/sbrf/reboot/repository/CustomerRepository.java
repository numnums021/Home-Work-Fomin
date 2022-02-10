package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Customer;
import lombok.NonNull;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {

    boolean createCustomer(@NonNull String userName,@NonNull String eMail) throws SQLException;

    List<Customer> getAll() throws SQLException;

    Customer getCustomer(@NonNull Long id) throws SQLException;

    boolean updateCustomer(@NonNull Long id, @NonNull String name, @NonNull String eMail) throws SQLException;

    boolean deleteCustomer(@NonNull Long id) throws SQLException;

    boolean checkCustomer(@NonNull String name) throws SQLException;
}
