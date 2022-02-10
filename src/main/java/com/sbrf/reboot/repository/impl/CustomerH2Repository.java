package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import java.nio.channels.ConnectionPendingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";
    private final String USER = "danya";
    private final String PASS = "12345678";

    public CustomerH2Repository() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
    }

    private Optional<Connection> getConnection() throws SQLException {
        return Optional.ofNullable(DriverManager.getConnection(DB_URL, USER, PASS));
    }

    @Override
    public boolean createCustomer(String name, String eMail) throws SQLException {
        int rows;

        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Customer (name, eMail) VALUES (?, ?)")) {

                pStmt.setString(1, name);
                pStmt.setString(2, eMail);
                rows = pStmt.executeUpdate();

            }
        }

        return rows > 0;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT * from Customer")) {

                ResultSet resultSet = pStmt.executeQuery();

                while (resultSet.next()) {
                    customers.add(new Customer(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3))
                    );
                }
            }
        }

        return customers;
    }


    @Override
    public Customer getCustomer(Long id) throws SQLException {
        Customer newCustomer = new Customer();

        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT * from Customer WHERE id = ?")) {

                pStmt.setLong(1, id);
                ResultSet resultSet = pStmt.executeQuery();

                if (resultSet.next()) {
                    newCustomer = (new Customer(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3)));
                }
            }
        }

        return newCustomer;
    }

    @Override
    public boolean updateCustomer(Long id, String name, String eMail) throws SQLException {
        int rows;

        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("UPDATE Customer SET" +
                    " name = ?, eMail = ? WHERE id = ?;")) {

                pStmt.setString(1, name);
                pStmt.setString(2, eMail);
                pStmt.setLong(3, id);

                rows = pStmt.executeUpdate();
            }
        }

        return rows > 0;
    }

    @Override
    public boolean deleteCustomer(Long id) throws SQLException {
        int rows;
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("DELETE FROM Customer WHERE (id = ?)")) {
                pStmt.setLong(1, id);
                rows = pStmt.executeUpdate();
            }
        }

        return rows > 0;
    }

}


