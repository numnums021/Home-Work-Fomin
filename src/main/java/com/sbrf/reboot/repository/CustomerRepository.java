package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Customer;
import lombok.NonNull;

import java.util.List;

public interface CustomerRepository {

    boolean createCustomer(@NonNull String userName,@NonNull String eMail);

    List<Customer> getAll();

    Customer getCustomer(@NonNull Long id) ;

    boolean updateCustomer(@NonNull Long id, @NonNull String name, @NonNull String eMail);

    boolean deleteCustomer(@NonNull Long id);

    boolean checkCustomer(@NonNull String name);

    boolean customerIsExist(@NonNull String name, @NonNull String eMail);

}
