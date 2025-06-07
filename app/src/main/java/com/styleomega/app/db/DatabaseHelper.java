package com.styleomega.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // Added import
import java.sql.SQLException;

public class DatabaseHelper {

    // IMPORTANT: User needs to configure these details based on their PostgreSQL setup.
    private static final String DB_URL = "jdbc:postgresql://YOUR_DB_HOST:YOUR_DB_PORT/YOUR_DB_NAME";
    private static final String USER = "YOUR_DB_USER";
    private static final String PASS = "YOUR_DB_PASSWORD";

    public Connection connect() {
        Connection conn = null;
        try {
            // Modern JDBC drivers (Type 4) usually don't require Class.forName to be called explicitly
            // as they are registered via the Service Provider Interface (SPI) mechanism.
            // Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.err.println("Connection Failed: " + e.getMessage());
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) { // Only needed if Class.forName is used
            System.err.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }*/
        return conn;
    }

    // Example method (will be expanded later)
    public void testConnection() {
        Connection conn = connect();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insertCreditCard(String cardNumber, String expiryDate, String cvv) {
        String sql = "INSERT INTO credit_cards(card_number, expiry_date, cvv) VALUES(?, ?, ?)"; // Example SQL
        Connection conn = connect();
        if (conn == null) {
            System.err.println("Cannot insert credit card, no database connection.");
            return false;
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, expiryDate);
            pstmt.setString(3, cvv);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting credit card: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
