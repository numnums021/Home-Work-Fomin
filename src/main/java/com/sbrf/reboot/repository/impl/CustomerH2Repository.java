package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.nio.channels.ConnectionPendingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerH2Repository implements CustomerRepository {

    private final String DB_URL = "jdbc:h2:~/my_db";
    private final String USER = "danya";
    private final String PASS = "12345678";

    public CustomerH2Repository() {
        createTable();
    }

    private Optional<Connection> getConnection() throws SQLException {
        return Optional.ofNullable(DriverManager.getConnection(DB_URL, USER, PASS));
    }

    private void createTable() {
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS CUSTOMER" +
                    "(id INT auto_increment, name VARCHAR(255), email VARCHAR(255), PRIMARY KEY (id));")) {

                pStmt.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean createCustomer(String name, String eMail) {

        if (customerIsExist(name, eMail))
            return false;

        int rows = 0;
        try (Connection conn = getConnection().orElseThrow(SQLException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Customer (name, eMail) VALUES (?, ?)")) {

                pStmt.setString(1, name);
                pStmt.setString(2, eMail);
                rows = pStmt.executeUpdate();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return rows > 0;
    }

    @Override
    public List<Customer> getAll() {
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
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return customers;
    }


    @Override
    public Customer getCustomer(Long id) {
        Customer newCustomer = null;

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
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return newCustomer;
    }

    @Override
    public boolean updateCustomer(Long id, String name, String eMail){

        int rows = 0;
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("UPDATE Customer SET" +
                    " name = ?, eMail = ? WHERE id = ?;")) {

                pStmt.setString(1, name);
                pStmt.setString(2, eMail);
                pStmt.setLong(3, id);

                rows = pStmt.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return rows > 0;
    }

    @Override
    public boolean deleteCustomer(Long id){
        int rows = 0;
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("DELETE FROM Customer WHERE (id = ?)")) {
                pStmt.setLong(1, id);
                rows = pStmt.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return rows > 0;
    }

    @Override
    public boolean checkCustomer(String name){
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT DISTINCT name FROM Customer WHERE (name = ?);")) {

                pStmt.setString(1, name);

                ResultSet resultSet = pStmt.executeQuery();

                return (resultSet.next());
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean customerIsExist(String name, String eMail){
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT DISTINCT name FROM Customer WHERE (name = ? AND eMail = ?);")) {

                pStmt.setString(1, name);
                pStmt.setString(2, eMail);

                ResultSet resultSet = pStmt.executeQuery();

                return (resultSet.next());
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return false;
    }
}


