package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "danya";
    private final String PASS = "12345678";

    private Connection conn;

    public CustomerH2Repository() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        conn = getConnection();
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement pStmt = conn.prepareStatement("SELECT * from Customer")) {
            ResultSet resultSet =  pStmt.executeQuery();
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3))
                );
            }
            conn.close();
        }

        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) throws SQLException {
        boolean result;
        conn = getConnection();
            try (PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Customer (name, eMail) VALUES (?, ?)")) {

                pStmt.setString(1, name);
                pStmt.setString(2, eMail);

                result = pStmt.execute();
                conn.close();
            }

        return result;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection (DB_URL, USER,PASS);
    }

}


