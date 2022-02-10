package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Customer;
import lombok.NonNull;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {

    boolean createCustomer(@NonNull String userName, String eMail) throws SQLException;

    List<Customer> getAll() throws SQLException;

}
