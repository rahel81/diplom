package ru.netology.data;

import lombok.val;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseSQL {

    private BaseSQL() {
    }

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try (FileInputStream appProperties = new FileInputStream("./application.properties")) {
            Properties appProp = new Properties();
            appProp.load(appProperties);
            final String DB_URL = appProp.getProperty("spring.datasource.url");
            final String DB_USER = appProp.getProperty("spring.datasource.username");
            final String DB_PASSWORD = appProp.getProperty("spring.datasource.password");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }

    public static String getPaymentId() {
        String paymentId = null;
        try (val conn = getConnection();
             val statusStmt = conn.createStatement()) {
            val idSQL = "SELECT payment_id FROM order_entity order by created desc limit 1;";
            try (val rs = statusStmt.executeQuery(idSQL)) {
                if (rs.next()) {
                    paymentId = rs.getString("payment_id");
                }
            }
            return paymentId;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getStatusDebitCardPayment(String paymentId) {
        String status = null;
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
            return status;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getStatusCreditPayment(String paymentId) {
        String statusSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        try (val conn = getConnection();
             val prepareStmt = conn.prepareStatement(statusSQL)) {
            prepareStmt.setString(1, paymentId);
            val resultSet = prepareStmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getAmountPayment(String paymentId) {
        String amount = null;
        String amountSQL = "SELECT amount FROM payment_entity WHERE transaction_id =?; ";
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(amountSQL)) {
            statusStmt.setString(1, paymentId);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    amount = rs.getString("amount");
                }
            }
            return amount;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void cleanBase() {
        String deleteOrder = "DELETE FROM order_entity;";
        String deletePayment = "DELETE FROM payment_entity;";
        String deleteCreditRequest = "DELETE FROM credit_request_entity;";

        try (val conn = BaseSQL.getConnection();
             val deleteOrderStmt = conn.createStatement();
             val deletePaymentStmt = conn.createStatement();
             val deleteCreditRequestStmt = conn.createStatement()
        ) {
            deleteOrderStmt.executeUpdate(deleteOrder);
            deletePaymentStmt.executeUpdate(deletePayment);
            deleteCreditRequestStmt.executeUpdate(deleteCreditRequest);

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}

